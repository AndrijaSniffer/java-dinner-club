package com.ates.dinnerClub.controllers;

import com.ates.dinnerClub.classes.InvitationId;
import com.ates.dinnerClub.classes.dto.invitation.CreateOrUpdateInvitationDTO;
import com.ates.dinnerClub.classes.dto.invitation.InvitationDTO;
import com.ates.dinnerClub.services.IInvitationService;
import com.ates.dinnerClub.services.implementations.InvitationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("invitation")
public class InvitationController {
    public final IInvitationService invitationService;

    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @GetMapping()
    public ResponseEntity<List<InvitationDTO>> getAllInvitations() {
        return ResponseEntity.ok(this.invitationService.getAllInvitations());
    }

    @GetMapping("byGuestAndEvent")
    public ResponseEntity<InvitationDTO> getInvitationById(@ModelAttribute InvitationId id) {
        return ResponseEntity.ok(this.invitationService.getInvitationById(id));
    }

    @PostMapping()
    public ResponseEntity<InvitationDTO> createInvitation(@RequestBody CreateOrUpdateInvitationDTO invitationDTO) {
        invitationDTO.setAccepted(false);
        invitationDTO.setAttended(false);
        return ResponseEntity.ok(this.invitationService.createOrUpdateInvitation(invitationDTO));
    }

    @PutMapping()
    public ResponseEntity<InvitationDTO> updateInvitation(@RequestBody CreateOrUpdateInvitationDTO invitationDTO) {
        InvitationDTO invitation = this.invitationService.getInvitationById(new InvitationId(invitationDTO.getGuestId(), invitationDTO.getEventId()));

        // This checks if the invitation is already accepted or attended so that it cannot be changed to false. No takesies backsies.
        // The requirements aren't clear on what should happen if the invitation is already accepted or attended.
        if ((invitation.isAccepted() && !invitationDTO.isAccepted()) || (invitation.isAttended() && !invitationDTO.isAttended())) {
            throw new IllegalArgumentException("Invitation is already accepted or attended");
        }

        return ResponseEntity.ok(this.invitationService.createOrUpdateInvitation(invitationDTO));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteInvitation(@ModelAttribute InvitationId id) {
        this.invitationService.deleteInvitation(id);

        return ResponseEntity.noContent().build();
    }
}
