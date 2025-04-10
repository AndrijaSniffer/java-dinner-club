package com.ates.dinnerClub.controllers;

import com.ates.dinnerClub.classes.InvitationId;
import com.ates.dinnerClub.classes.dto.ErrorResponse;
import com.ates.dinnerClub.classes.dto.invitation.CreateOrUpdateInvitationDTO;
import com.ates.dinnerClub.classes.dto.invitation.InvitationDTO;
import com.ates.dinnerClub.services.IInvitationService;
import com.ates.dinnerClub.services.implementations.InvitationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("invitation")
public class InvitationController {
    public final IInvitationService invitationService;

    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @GetMapping()
    @Operation(
            summary = "Get all invitations",
            description = "Returns all existing invitations."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Invitations retrieved successfully", content = @Content(schema = @Schema(implementation = InvitationDTO[].class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<InvitationDTO>> getAllInvitations() {
        return ResponseEntity.ok(this.invitationService.getAllInvitations());
    }

    @GetMapping("byGuestAndEvent")
    @Operation(
            summary = "Get invitation by guest id and event id",
            description = "Returns an invitation by guest id and event id."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Invitation retrieved successfully", content = @Content(schema = @Schema(implementation = InvitationDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Invitation not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<InvitationDTO> getInvitationById(@ModelAttribute @Valid InvitationId id) {
        return ResponseEntity.ok(this.invitationService.getInvitationById(id));
    }

    @PostMapping()
    @Operation(
            summary = "Create invitation",
            description = "Creates a new invitation."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Invitation created successfully", content = @Content(schema = @Schema(implementation = InvitationDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<InvitationDTO> createInvitation(@RequestBody CreateOrUpdateInvitationDTO invitationDTO) {
        invitationDTO.setAccepted(false);
        invitationDTO.setAttended(false);
        return ResponseEntity.ok(this.invitationService.createOrUpdateInvitation(invitationDTO));
    }

    @PutMapping()
    @Operation(
            summary = "Update invitation",
            description = "Updates an existing invitation."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Invitation updated successfully", content = @Content(schema = @Schema(implementation = InvitationDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Invitation not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<InvitationDTO> updateInvitation(@RequestBody CreateOrUpdateInvitationDTO invitationDTO) {
        InvitationDTO invitation = this.invitationService.getInvitationById(new InvitationId(invitationDTO.getGuestId(), invitationDTO.getEventId()));

        // This checks if the invitation is already accepted or attended so that it cannot be changed to false. No takesies backsies.
        // The requirements aren't clear on what should happen if the invitation is already accepted or attended.
        if ((invitation.isAccepted() && !invitationDTO.isAccepted()) || (invitation.isAttended() && !invitationDTO.isAttended())) {
            throw new IllegalArgumentException("Invitation is already accepted or attended");
        }

        return ResponseEntity.ok(this.invitationService.createOrUpdateInvitation(invitationDTO));
    }

    @DeleteMapping()
    @Operation(
            summary = "Delete invitation",
            description = "Deletes an existing invitation."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Always returns")
    })
    public ResponseEntity<Void> deleteInvitation(@ModelAttribute InvitationId id) {
        this.invitationService.deleteInvitation(id);

        return ResponseEntity.noContent().build();
    }
}
