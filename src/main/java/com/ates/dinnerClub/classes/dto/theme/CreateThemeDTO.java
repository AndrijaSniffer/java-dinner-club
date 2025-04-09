package com.ates.dinnerClub.classes.dto.theme;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO object for creating a new theme")
public class CreateThemeDTO {
    @Schema(
            description = "Name of the theme (must be unique)",
            example = "Black Tie Gala"
    )
    @NonNull
    @NotBlank
    private String name;
}
