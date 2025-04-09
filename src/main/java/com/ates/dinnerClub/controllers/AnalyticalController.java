package com.ates.dinnerClub.controllers;

import com.ates.dinnerClub.classes.dto.analytics.FrequentNoShowOffendersRecord;
import com.ates.dinnerClub.classes.dto.analytics.MostReliableGuestRecord;
import com.ates.dinnerClub.classes.dto.analytics.ThemeLoyaltyRecord;
import com.ates.dinnerClub.classes.dto.analytics.UpcomingEventsWithLowAttendance;
import com.ates.dinnerClub.services.IAnalyticalService;
import com.ates.dinnerClub.services.implementations.AnalyticalService;
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
    public ResponseEntity<List<MostReliableGuestRecord>> getTop5MostReliableGuests() {
        return ResponseEntity.ok(analyticalService.getTop5MostReliableGuests());
    }

    @GetMapping("frequentNoShowOffenders")
    public ResponseEntity<List<FrequentNoShowOffendersRecord>> getFrequentNoShowOffenders() {
        return ResponseEntity.ok(analyticalService.getFrequentNoShowOffenders());
    }

    @GetMapping("upcomingEventsWithLowAttendance")
    public ResponseEntity<List<UpcomingEventsWithLowAttendance>> getUpcomingEventsWithLowAttendance() {
        return ResponseEntity.ok(analyticalService.getUpcomingEventsWithLowAttendance());
    }

    @GetMapping("themeLoyalty")
    public ResponseEntity<List<ThemeLoyaltyRecord>> themeLoyalty() {
        return ResponseEntity.ok(this.analyticalService.themeLoyalty());
    }
}
