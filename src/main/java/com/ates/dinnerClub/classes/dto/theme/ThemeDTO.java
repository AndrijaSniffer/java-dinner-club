package com.ates.dinnerClub.classes.dto.theme;

import com.ates.dinnerClub.entities.Theme;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "General theme DTO for GET and PUT requests")
public class ThemeDTO {
    @Schema(
            description = "Unique Theme ID",
            example = "1"
    )
    @NotNull
    @Min(1)
    private int id;

    @Schema(
            description = "Name of the theme (must be unique)",
            example = "80s Retro Night"
    )
    @NotNull
    @NotBlank
    private String name;

    public ThemeDTO(Theme theme) {
        this.id = theme.getId();
        this.name = theme.getName();
    }
}
