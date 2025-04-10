package com.ates.dinnerClub.controllers;

import com.ates.dinnerClub.classes.dto.ErrorResponse;
import com.ates.dinnerClub.classes.dto.guest.CreateGuestDTO;
import com.ates.dinnerClub.classes.dto.guest.GuestDTO;
import com.ates.dinnerClub.services.IGuestService;
import com.ates.dinnerClub.services.implementations.GuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("guest")
public class GuestController {
    private final IGuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping()
    @Operation(
            summary = "Get all guests",
            description = "Returns all existing guests."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Guests retrieved successfully", content = @Content(schema = @Schema(implementation = GuestDTO[].class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<List<GuestDTO>> getAllGuests() {
        return ResponseEntity.ok(this.guestService.getAllGuests());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get guest by ID",
            description = "Returns a guest by it's ID. Returns 404 if guest is not found"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Guest retrieved successfully", content = @Content(schema = @Schema(implementation = GuestDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Guest not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<GuestDTO> getGuestById(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.guestService.getGuestById(id));
    }

    @GetMapping("/email/{email}")
    @Operation(
            summary = "Get guest by email",
            description = "Returns a guest by it's email. Returns 404 if guest is not found"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Guest retrieved successfully", content = @Content(schema = @Schema(implementation = GuestDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Guest not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<GuestDTO> getGuestByEmail(@PathVariable @NotNull @NotBlank String email) {
        return ResponseEntity.ok(this.guestService.getGuestByEmail(email));
    }

    @GetMapping("/name/first/{firstName}")
    @Operation(
            summary = "Get guest by their first name",
            description = "Returns a guest by their first name. Returns 404 if guest is not found"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Guest retrieved successfully", content = @Content(schema = @Schema(implementation = GuestDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Guest not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<List<GuestDTO>> getGuestsByFirstName(@PathVariable @NotNull @NotBlank String firstName) {
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        return ResponseEntity.ok(this.guestService.getGuestsByFirstName(firstName));
    }

    @GetMapping("/name/last/{lastName}")
    @Operation(
            summary = "Get guest by their last name",
            description = "Returns a guest by their last name. Returns 404 if guest is not found"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Guest retrieved successfully", content = @Content(schema = @Schema(implementation = GuestDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Guest not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<List<GuestDTO>> getGuestsByLastName(@PathVariable @NotNull @NotBlank String lastName) {
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        return ResponseEntity.ok(this.guestService.getGuestsByLastName(lastName));
    }

    @GetMapping("/name/full/{fullName}")
    @Operation(
            summary = "Get guest by their full name",
            description = "Returns a guest by their full name. Returns 404 if guest is not found"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Guest retrieved successfully", content = @Content(schema = @Schema(implementation = GuestDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Guest not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<GuestDTO> getGuestsByFullName(@PathVariable @NotNull @NotBlank String fullName) {
        String[] names = fullName.split(" ");

        String firstName = names[0];
        String lastName = names[1];

        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);

        // The code handles both cases where the last name is before the first name and vice versa.
        try {
            return ResponseEntity.ok(this.guestService.getGuestByFirstAndLastName(firstName, lastName));
        } catch (NoSuchElementException e) {
            return ResponseEntity.ok(this.guestService.getGuestByFirstAndLastName(lastName, firstName));
        }
    }

    @PostMapping()
    @Operation(
            summary = "Create a new guest",
            description = "Creates a new guest. Returns 400 if guest is not valid. Returns 500 if guest cannot be created"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Guest retrieved successfully", content = @Content(schema = @Schema(implementation = GuestDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<GuestDTO> addGuest(@RequestBody @Valid CreateGuestDTO guest) {
        guest.setFirstName(guest.getFirstName().substring(0, 1).toUpperCase() + guest.getFirstName().substring(1));
        guest.setLastName(guest.getLastName().substring(0, 1).toUpperCase() + guest.getLastName().substring(1));
        return ResponseEntity.ok(this.guestService.addGuest(guest));
    }

    @PutMapping()
    @Operation(
            summary = "Update an existing guest",
            description = "Updates an existing guest. Returns 400 if guest is not valid. Returns 500 if guest cannot be updated"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Guest retrieved successfully", content = @Content(schema = @Schema(implementation = GuestDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<GuestDTO> updateGuest(@RequestBody @Valid GuestDTO guest) {
        return ResponseEntity.ok(this.guestService.updateGuest(guest));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete an existing guest",
            description = "Deletes an existing guest. Returns 400 if guest is not valid. Returns 500 if guest cannot be deleted"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Always returns"),
    })
    public ResponseEntity<Void> deleteGuest(@PathVariable @Min(1) long id) {
        this.guestService.deleteGuest(id);

        return ResponseEntity.noContent().build();
    }
}
