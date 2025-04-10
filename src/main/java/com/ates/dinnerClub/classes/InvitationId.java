package com.ates.dinnerClub.classes;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Composite key for the Invitation entity")
public class InvitationId implements Serializable {
    @NotNull
    @Min(1)
    private long guest_id;

    @NotNull
    @Min(1)
    private long event_id;
}
