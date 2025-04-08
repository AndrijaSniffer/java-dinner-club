package com.ates.dinnerClub.classes.dto.invitation;

import com.ates.dinnerClub.entities.Invitation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateInvitationDTO {
    private int guestId;
    private int eventId;
    private boolean isAccepted;
    private boolean isAttended;

    public CreateOrUpdateInvitationDTO(Invitation invitation) {
        this.guestId = invitation.getGuest().getId();
        this.eventId = invitation.getEvent().getId();
        this.isAccepted = invitation.isAccepted();
        this.isAttended = invitation.isAttended();
    }
}
