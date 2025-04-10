package com.ates.dinnerClub.classes.dto.analytics;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Most Reliable Guest Data class")
public record MostReliableGuestRecord(
        @Schema(description = "Guest ID", example = "1")
                @Min(1)
        long id,

        @Schema(description = "Guest First Name", example = "John")
                @NotNull
                @NotBlank
        String firstName,

        @Schema(description = "Guest Last Name", example = "Doe")
                @NotNull
                @NotBlank
        String lastName,

        @Schema(description = "Number of times the guest attended events", example = "5")
        long attendedEvents
) {}
