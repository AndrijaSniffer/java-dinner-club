package com.ates.dinnerClub.classes.dto.guest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO object for creating a new guest")
public class CreateGuestDTO {
    @Schema(
            description = "First name of the guest",
            example = "John"
    )
    @NotNull
    @NotBlank
    private String firstName;

    @Schema(
            description = "Last name of the guest",
            example = "Doe"
    )
    @NotNull
    @NotBlank
    private String lastName;

    @Schema(
            description = "Email of the guest",
            example = "john.doe@example.com"
    )
    @NotNull
    @NotBlank
    @Email
    private String email;

    @Schema(
            description = "Phone number of the guest",
            example = "+381 641234567"
    )
    @NotNull
    @NotBlank
    private String phoneNumber;
}
