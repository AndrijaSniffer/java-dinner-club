package com.ates.dinnerClub.services.implementations;

import com.ates.dinnerClub.classes.InvitationId;
import com.ates.dinnerClub.classes.dto.invitation.CreateOrUpdateInvitationDTO;
import com.ates.dinnerClub.classes.dto.invitation.InvitationDTO;
import com.ates.dinnerClub.entities.Invitation;
import com.ates.dinnerClub.repositories.IInvitationRepo;
import com.ates.dinnerClub.services.IInvitationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationService implements IInvitationService {
    public final IInvitationRepo invitationRepo;
    public final EventService eventService;
    public final GuestService guestService;

    public InvitationService(IInvitationRepo invitationRepo, EventService eventService, GuestService guestService) {
        this.invitationRepo = invitationRepo;
        this.eventService = eventService;
        this.guestService = guestService;
    }

    @Override
    public List<InvitationDTO> getAllInvitations() {
        return this.invitationRepo.findAll().stream().map(InvitationDTO::new).toList();
    }

    @Override
    public InvitationDTO getInvitationById(InvitationId id) {
        return this.invitationRepo.findByGuestIdAndEventId(id.getGuest_id(), id.getEvent_id()).map(InvitationDTO::new).orElseThrow();
    }

    @Override
    public InvitationDTO createOrUpdateInvitation(CreateOrUpdateInvitationDTO invitationDTO) {
        if(invitationDTO == null || invitationDTO.getGuestId() <= 0 || invitationDTO.getEventId() <= 0) {
            throw new IllegalArgumentException("Invitation data is invalid");
        } else {
            Invitation invitation = new Invitation();
            invitation.setGuest(this.guestService.getGuestByIdForCreation(invitationDTO.getGuestId()));
            invitation.setEvent(this.eventService.getEventByIdForCreation(invitationDTO.getEventId()));
            invitation.setId(new InvitationId(invitationDTO.getGuestId(), invitationDTO.getEventId()));
            invitation.setAccepted(invitationDTO.isAccepted());
            invitation.setAttended(invitationDTO.isAttended());
            this.invitationRepo.save(invitation);

            return new InvitationDTO(this.invitationRepo.save(invitation));
        }
    }

//    @Override
//    public InvitationDTO updateInvitation(CreateOrUpdateInvitationDTO invitationDTO) {
//        if(invitationDTO == null || invitationDTO.getGuestId() <= 0 || invitationDTO.getEventId() <= 0) {
//            throw new IllegalArgumentException("Invitation data is invalid");
//        } else {
//            Invitation invitation = new Invitation();
//            invitation.setGuest(this.guestService.getGuestByIdForCreation(invitationDTO.getGuestId()));
//            invitation.setEvent(this.eventService.getEventByIdForCreation(invitationDTO.getEventId()));
//            invitation.setId(new InvitationId(invitationDTO.getGuestId(), invitationDTO.getEventId()));
//            invitation.setAccepted(invitationDTO.isAccepted());
//            invitation.setAttended(invitationDTO.isAttended());
//            this.invitationRepo.save(invitation);
//
//            return new InvitationDTO(this.invitationRepo.save(invitation));
//        }
//    }

    @Override
    @Transactional
    public void deleteInvitation(InvitationId id) {
        this.invitationRepo.deleteByGuestIdAndEventId(id.getGuest_id(), id.getEvent_id());
    }
}
