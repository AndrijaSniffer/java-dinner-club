package com.ates.dinnerClub.classes.dto.analytics;

import com.ates.dinnerClub.classes.enums.EventStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Schema(description = "Upcoming Events with Low Attendance Data class. EventStatus for status")
public record UpcomingEventsWithLowAttendance(
        @Schema(description = "Event ID", example = "1")
        @Min(1)
        long id,

        @Schema(description = "Event status", example = "COMPLETED")
        @NotNull
        EventStatus status,

        @Schema(description = "Event location", example = "123 Main St, Anytown")
        @NotNull
        @NotBlank
        String location,

        @Schema(description = "Event date", example = "2023-05-01")
        @NotNull
        @DateTimeFormat
        Date date,

        @Schema(description = "Number of attendees", example = "2")
        long eventAttendance
) {
}
