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
@Schema(description = "Request DTO object for updating an event")
public class UpdateEventDTO {
    @Schema(
            description = "ID of the event",
            example = "1"
    )
    @NotNull
    @Min(1)
    private long id;

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
    private long theme;

    public boolean compareToEntity(Event event) {
        return this.getId() == event.getId() && this.getLocation().equals(event.getLocation())
                && this.getDate().equals(event.getDate()) && this.getStatus().equals(event.getStatus())
                && this.getTheme() == event.getTheme().getId();
    }
}
