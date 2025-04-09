package com.ates.dinnerClub.controllers;

import com.ates.dinnerClub.classes.dto.ErrorResponse;
import com.ates.dinnerClub.classes.dto.theme.CreateThemeDTO;
import com.ates.dinnerClub.classes.dto.theme.ThemeDTO;
import com.ates.dinnerClub.services.IThemeService;
import com.ates.dinnerClub.services.implementations.ThemeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("theme")
public class ThemeController {
    private final IThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping()
    @Operation(
            summary = "Get all themes",
            description = "Returns all existing themes."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Themes retrieved successfully", content = @Content(schema = @Schema(implementation = ThemeDTO[].class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<List<ThemeDTO>> getAllThemes() {
        return ResponseEntity.ok(this.themeService.getAllThemes());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get theme by ID",
            description = "Returns a theme by it's ID. Returns 404 if theme is not found"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Theme successfully returned", content = @Content(schema = @Schema(implementation = ThemeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Theme not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<ThemeDTO> getThemeById(@PathVariable @Min(1) int id) {
        return ResponseEntity.ok(this.themeService.getThemeById(id));
    }

    @PostMapping()
    @Operation(
            summary = "Create a new theme",
            description = "Creates a theme with a unique name. Returns 400 if the name is invalid or already exists."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Theme created successfully", content = @Content(schema = @Schema(implementation = ThemeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request or duplicate theme name", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<ThemeDTO> addTheme(@RequestBody @Valid CreateThemeDTO themeDTO) {
        return ResponseEntity.ok(this.themeService.addTheme(themeDTO));
    }

    @PutMapping()
    @Operation(
            summary = "Update an existing theme",
            description = "Updates a pre-existing theme with a new name. Returns 404 if the theme is not found."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Theme updated successfully", content = @Content(schema = @Schema(implementation = ThemeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request or duplicate theme name", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Theme not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<ThemeDTO> updateTheme(@RequestBody @Valid ThemeDTO themeDTO) {
        return ResponseEntity.ok(this.themeService.updateTheme(themeDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a theme",
            description = "Deletes a theme by it's ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Default response, it returns no matter what"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<Void> deleteTheme(@PathVariable @Min(1) int id) {
        this.themeService.deleteTheme(id);

        return ResponseEntity.noContent().build();
    }
}
