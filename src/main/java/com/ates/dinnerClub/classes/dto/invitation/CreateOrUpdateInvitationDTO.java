package com.ates.dinnerClub.classes.dto.invitation;

import com.ates.dinnerClub.entities.Invitation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for creating or updating an invitation.")
public class CreateOrUpdateInvitationDTO {
    @Schema(
            description = "ID of the guest associated with the invitation.",
            example = "1"
    )
    @Min(1)
    private long guestId;

    @Schema(
            description = "ID of the event associated with the invitation.",
            example = "1"
    )
    @Min(1)
    private long eventId;

    @Schema(
            description = "Indicates if the invitation has been accepted.",
            example = "true"
    )
    private boolean isAccepted;

    @Schema(
            description = "Indicates if the guest has attended the event.",
            example = "false"
    )
    private boolean isAttended;

    public CreateOrUpdateInvitationDTO(Invitation invitation) {
        this.guestId = invitation.getGuest().getId();
        this.eventId = invitation.getEvent().getId();
        this.isAccepted = invitation.isAccepted();
        this.isAttended = invitation.isAttended();
    }
}
