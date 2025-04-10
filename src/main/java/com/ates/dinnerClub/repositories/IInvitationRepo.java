package com.ates.dinnerClub.repositories;

import com.ates.dinnerClub.entities.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IInvitationRepo extends JpaRepository<Invitation, Integer> {
    Optional<Invitation> findByGuestIdAndEventId(long guestId, long eventId);

    @Query("SELECT i FROM Invitation i WHERE i.event.id = :eventId AND i.isAccepted = true")
    List<Invitation> findAllByEventIdAndTimeFrame(@Param("eventId") long eventId);

    @Query("SELECT i FROM Invitation i WHERE i.guest.id = :guestId AND i.isAccepted = true AND i.isAttended = true")
    List<Invitation> findAllByGuestIdAndAcceptedAndAttended(@Param("guestId") long guestId);

    @Query("SELECT i FROM Invitation i WHERE i.event.theme.id = :themeId AND i.event.status = 'COMPLETED'")
    List<Invitation> findAllByThemeIdAndStatusCOMPLETED(@Param("themeId") long themeId);

    void deleteByGuestIdAndEventId(long guestId, long eventId);
}
