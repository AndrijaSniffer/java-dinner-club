package com.ates.dinnerClub.repositories;

import com.ates.dinnerClub.classes.enums.EventStatus;
import com.ates.dinnerClub.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEventRepo extends JpaRepository<Event, Integer> {
    List<Event> findAllByStatus(EventStatus status);
}
