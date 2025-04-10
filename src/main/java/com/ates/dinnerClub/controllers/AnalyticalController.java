package com.ates.dinnerClub.controllers;

import com.ates.dinnerClub.classes.dto.ErrorResponse;
import com.ates.dinnerClub.classes.dto.analytics.FrequentNoShowOffendersRecord;
import com.ates.dinnerClub.classes.dto.analytics.MostReliableGuestRecord;
import com.ates.dinnerClub.classes.dto.analytics.ThemeLoyaltyRecord;
import com.ates.dinnerClub.classes.dto.analytics.UpcomingEventsWithLowAttendance;
import com.ates.dinnerClub.services.IAnalyticalService;
import com.ates.dinnerClub.services.implementations.AnalyticalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("analytical")
public class AnalyticalController {
    private final IAnalyticalService analyticalService;

    public AnalyticalController(AnalyticalService analyticalService) {
        this.analyticalService = analyticalService;
    }

    @GetMapping("mostReliableGuest")
    @Operation(
            summary = "Top 5 most reliable guests",
            description = "Endpoint for getting the top 5 most reliable guests"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Analytics retrieved successfully", content = @Content(schema = @Schema(implementation = MostReliableGuestRecord[].class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<MostReliableGuestRecord>> getTop5MostReliableGuests() {
        return ResponseEntity.ok(analyticalService.getTop5MostReliableGuests());
    }

    @GetMapping("frequentNoShowOffenders")
    @Operation(
            summary = "Frequent no show offenders",
            description = "Endpoint for getting the frequent no show offenders"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Analytics retrieved successfully", content = @Content(schema = @Schema(implementation = FrequentNoShowOffendersRecord[].class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<FrequentNoShowOffendersRecord>> getFrequentNoShowOffenders() {
        return ResponseEntity.ok(analyticalService.getFrequentNoShowOffenders());
    }

    @GetMapping("upcomingEventsWithLowAttendance")
    @Operation(
            summary = "Upcoming events with low attendance",
            description = "Endpoint for getting the upcoming events with low attendance"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Analytics retrieved successfully", content = @Content(schema = @Schema(implementation = UpcomingEventsWithLowAttendance[].class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<UpcomingEventsWithLowAttendance>> getUpcomingEventsWithLowAttendance() {
        return ResponseEntity.ok(analyticalService.getUpcomingEventsWithLowAttendance());
    }

    @GetMapping("themeLoyalty")
    @Operation(
            summary = "Theme loyalty",
            description = "Endpoint for getting the theme loyalty"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Analytics retrieved successfully", content = @Content(schema = @Schema(implementation = ThemeLoyaltyRecord[].class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<ThemeLoyaltyRecord>> themeLoyalty() {
        return ResponseEntity.ok(this.analyticalService.themeLoyalty());
    }
}
