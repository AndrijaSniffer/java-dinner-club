package com.ates.dinnerClub.services;

import com.ates.dinnerClub.classes.InvitationId;
import com.ates.dinnerClub.classes.dto.invitation.CreateOrUpdateInvitationDTO;
import com.ates.dinnerClub.classes.dto.invitation.InvitationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IInvitationService {
    List<InvitationDTO> getAllInvitations();

    InvitationDTO getInvitationById(InvitationId id);

    InvitationDTO createOrUpdateInvitation(CreateOrUpdateInvitationDTO invitationDTO);

//    InvitationDTO updateInvitation(CreateOrUpdateInvitationDTO invitationDTO);

    void deleteInvitation(InvitationId id);
}
