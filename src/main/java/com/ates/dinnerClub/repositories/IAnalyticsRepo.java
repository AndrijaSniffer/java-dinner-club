package com.ates.dinnerClub.repositories;

import com.ates.dinnerClub.classes.dto.analytics.FrequentNoShowOffendersRecord;
import com.ates.dinnerClub.classes.dto.analytics.MostReliableGuestRecord;
import com.ates.dinnerClub.classes.dto.analytics.UpcomingEventsWithLowAttendanceRecord;
import com.ates.dinnerClub.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnalyticsRepo extends JpaRepository<Guest, Integer> {
    @Query(value = """
            SELECT g.id, g.first_name AS firstName, g.lASt_name AS lAStName, COUNT(*) AS attendedEvents
            FROM guest g, invitation i
            WHERE i.is_accepted = true AND i.is_attended = true
                AND i.guest_id = g.id
            GROUP BY g.id
            ORDER BY attendedEvents DESC
            LIMIT 5""",
            nativeQuery = true)
    List<MostReliableGuestRecord> findTop5MostReliableGuests();

    @Query(value = """
            SELECT
                accepted.id,
                accepted.first_name AS firstName,
                accepted.last_name AS lastName,
                accepted.accepted_events AS acceptedEvents,
                unattended.unattended_events AS unattendedEvents
            FROM
                (
                    SELECT
                        g.id,
                        g.first_name,
                        g.last_name,
                        COUNT(*) AS accepted_events
                    FROM
                        guest g, invitation i
                    WHERE
                        i.is_accepted = true
                        AND i.guest_id = g.id
                    GROUP BY
                        g.id
                    HAVING
                        COUNT(*) >= 3
                ) AS accepted,
                (
                    SELECT
                        g.id,
                        g.first_name,
                        g.last_name,
                        COUNT(*) AS unattended_events
                    FROM
                        guest g, invitation i
                    WHERE
                        i.is_attended = false
                        AND i.guest_id = g.id
                    GROUP BY
                        g.id
                    HAVING
                        COUNT(*) >= 2
                ) AS unattended
            WHERE
                accepted.id = unattended.id;""", nativeQuery = true)
    List<FrequentNoShowOffendersRecord> findFrequentNoShowOffenders();

    @Query(value = """
            SELECT
                e.id,
                e.status::text AS status,
                e.location,
                e.date,
                ea.event_attendance AS eventAttendance
            FROM
                (
                    SELECT
                        e.id,
                        e.status,
                        COUNT(*) AS event_attendance
                    FROM
                        "event" e, invitation i
                    WHERE
                        e.id = i.event_id
                        AND i.is_accepted = true
                    GROUP BY
                        e.id
                    HAVING
                        COUNT(*) < 5
                ) AS ea,
                "event" e
            WHERE
                e.id = ea.id
                AND ea.status LIKE 'UPCOMING'""",
            nativeQuery = true)
    List<UpcomingEventsWithLowAttendanceRecord> findUpcomingEventsWithLowAttendance();
}
