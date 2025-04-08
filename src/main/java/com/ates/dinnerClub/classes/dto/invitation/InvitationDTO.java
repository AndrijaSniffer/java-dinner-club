package com.ates.dinnerClub.classes.dto.invitation;

import com.ates.dinnerClub.classes.dto.event.EventDTO;
import com.ates.dinnerClub.classes.dto.guest.GuestDTO;
import com.ates.dinnerClub.entities.Invitation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitationDTO {
    private GuestDTO guest;
    private EventDTO event;
    private boolean isAccepted;
    private boolean isAttended;

    public InvitationDTO(Invitation invitation) {
        this.guest = new GuestDTO(invitation.getGuest());
        this.event = new EventDTO(invitation.getEvent());
        this.isAccepted = invitation.isAccepted();
        this.isAttended = invitation.isAttended();
    }
}
