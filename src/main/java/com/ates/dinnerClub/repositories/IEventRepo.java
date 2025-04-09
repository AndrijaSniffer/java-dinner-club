package com.ates.dinnerClub.repositories;

import com.ates.dinnerClub.classes.dto.guest.GuestInvitationProjection;
import com.ates.dinnerClub.classes.enums.EventStatus;
import com.ates.dinnerClub.entities.Event;
import com.ates.dinnerClub.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEventRepo extends JpaRepository<Event, Integer> {
    List<Event> findAllByStatus(EventStatus status);

    @Query(value = """
            SELECT g.id AS id,
            g.first_name AS firstName,
            g.last_name AS lastName,
            g.email AS email,
            g.phone_number AS phoneNumber,
            i.is_accepted AS isAccepted,
            i.is_attended AS isAttended
            FROM guest g, invitation i, "event" e
            WHERE i.event_id = ?1
            AND i.guest_id = g.id AND i.event_id = e.id;""", nativeQuery = true)
    List<GuestInvitationProjection> findAllGuestsByEventId(int eventId);

    @Query(value = """
            SELECT g.id AS id,
            g.first_name AS firstName,
            g.last_name AS lastName,
            g.email AS email,
            g.phone_number AS phoneNumber,
            i.is_accepted AS isAccepted,
            i.is_attended AS isAttended
            FROM guest g, invitation i, "event" e
            WHERE i.event_id = ?1 AND e.status = 'COMPLETED' AND i.is_accepted = true AND i.is_attended = false
            AND i.guest_id = g.id AND i.event_id = e.id;""", nativeQuery = true)
    List<GuestInvitationProjection> findAllGuestsWithoutAttendanceByEventId(int eventId);

    @Query(value = """
            SELECT g.*
            FROM guest g, invitation i, "event" e
            WHERE i.event_id = ?1 AND i.is_accepted = true
              AND i.guest_id = g.id AND i.event_id = e.id;""", nativeQuery = true)
    List<Guest> findAllGuestsThatAcceptedInvitationByEventId(int eventId);
}
