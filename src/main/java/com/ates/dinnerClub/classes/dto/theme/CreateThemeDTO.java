package com.ates.dinnerClub.classes.dto.theme;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Request DTO object for creating a new theme")
public class CreateThemeDTO {
    @Schema(
            description = "Name of the theme (must be unique)",
            example = "Black Tie Gala"
    )
    private String name;
}
