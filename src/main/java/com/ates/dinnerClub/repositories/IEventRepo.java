package com.ates.dinnerClub.repositories;

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

    @Query(value = "SELECT g.*, i.is_accepted, i.is_attended FROM guest g, invitation i, \"event\" e\n" +
            "WHERE i.event_id = ?1 AND i.guest_id = g.id AND i.event_id = e.id", nativeQuery = true)
    List<Guest> findAllGuestsByEventId(int eventId);
}
