package com.ates.dinnerClub.classes.dto.analytics;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

@Schema(description = "Frequent No Show Offenders Data class")
public record FrequentNoShowOffendersRecord(
        @Schema(
                description = "Guest ID",
                example = "1"
        )
                @Min(1)
        long id,

        @Schema(
                description = "Guest First Name",
                example = "John"
        )
                @NonNull
                @NotBlank
        String firstName,

        @Schema(
                description = "Guest Last Name",
                example = "Doe"
        )
                @NonNull
                @NotBlank
        String lastName,

        @Schema(
                description = "How many accepted events a guest has",
                example = "4"
        )
        long acceptedEvents,

        @Schema(
                description = "How many events a guest has not attended",
                example = "2"
        )
        long unattendedEvents
) {
}
