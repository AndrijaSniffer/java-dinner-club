package com.ates.dinnerClub.classes.dto.invitation;

import com.ates.dinnerClub.classes.dto.event.EventDTO;
import com.ates.dinnerClub.classes.dto.guest.GuestDTO;
import com.ates.dinnerClub.entities.Invitation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for representing an invitation.")
public class InvitationDTO {
    @Schema(
            description = "Guest from the invitation.",
            example = "{}"
    )
    @NonNull
    private GuestDTO guest;

    @Schema(
            description = "Event from the invitation.",
            example = "{}"
    )
    @NonNull
    private EventDTO event;

    @Schema(
            description = "Whether the guest has accepted the invitation.",
            example = "true"
    )
    private boolean isAccepted;

    @Schema(
            description = "Whether the guest has attended the event.",
            example = "false"
    )
    private boolean isAttended;

    public InvitationDTO(Invitation invitation) {
        this.guest = new GuestDTO(invitation.getGuest());
        this.event = new EventDTO(invitation.getEvent());
        this.isAccepted = invitation.isAccepted();
        this.isAttended = invitation.isAttended();
    }
}
