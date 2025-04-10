package com.ates.dinnerClub.classes.dto.guest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Record (data class) for a guest invitation join")
public record GuestInvitationRecord(
        @Schema(
                description = "Unique Guest ID",
                example = "1"
        )
        @NotNull
        @Min(1)
        long id,

        @Schema(
                description = "First name of the guest",
                example = "John"
        )
        @NotNull
        @NotBlank
        String firstName,

        @Schema(
                description = "Last name of the guest",
                example = "Doe"
        )
        @NotNull
        @NotBlank
        String lastName,

        @Schema(
                description = "Email of the guest",
                example = "john.doe@example.com"
        )
        @NotNull
        @NotBlank
        @Email
        String email,

        @Schema(
                description = "Phone number of the guest",
                example = "+381 641234567"
        )
        @NotNull
        @NotBlank
        String phoneNumber,

        @Schema(
                description = "Did the guest accept the invite?",
                example = "true"
        )
        @NotNull
        boolean isAccepted,


        @Schema(
                description = "Did the guest attend the event?",
                example = "false"
        )
        @NotNull
        boolean isAttended
) {}
