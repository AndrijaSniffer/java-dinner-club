package com.ates.dinnerClub.classes.dto.analytics;

import com.ates.dinnerClub.classes.dto.guest.GuestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Theme Loyalty Data class")
public record ThemeLoyaltyRecord(
        @Schema(description = "Guest that is loyal to the theme", example = "{}")
        GuestDTO guest,

        @Schema(description = "Theme Name", example = "80s Retro Night")
        @NotNull
        @NotBlank
        String theme
) {
}
