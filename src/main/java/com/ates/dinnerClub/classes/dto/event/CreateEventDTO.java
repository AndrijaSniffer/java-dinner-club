package com.ates.dinnerClub.classes.dto.event;

import com.ates.dinnerClub.classes.enums.EventStatus;
import com.ates.dinnerClub.entities.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO object for creating a new event")
public class CreateEventDTO {
    @Schema(
            description = "Location of the event",
            example = "The Grand Ballroom"
    )
    @NotNull
    @NotBlank
    private String location;

    @Schema(
            description = "Date of the event",
            example = "2023-05-01"
    )
    @NotNull
    @DateTimeFormat
    private Date date;

    @Schema(
            description = "Status of the event",
            example = "UPCOMING"
    )
    @NotNull
    @NotBlank
    private EventStatus status;

    @Schema(
            description = "Theme ID of the event",
            example = "1"
    )
    @NotNull
    @Min(1)
    private long themeId;

    public CreateEventDTO(Event event) {
        this.location = event.getLocation();
        this.date = event.getDate();
        this.status = event.getStatus();
        this.themeId = event.getTheme().getId();
    }
}
