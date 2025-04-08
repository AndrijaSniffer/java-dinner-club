package com.ates.dinnerClub.repositories;

import com.ates.dinnerClub.entities.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IInvitationRepo extends JpaRepository<Invitation, Integer> {
    Optional<Invitation> findByGuestIdAndEventId(int guestId, int eventId);

    void deleteByGuestIdAndEventId(int guestId, int eventId);
}
