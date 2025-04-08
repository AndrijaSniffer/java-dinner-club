package com.ates.dinnerClub.classes.dto.event;

import com.ates.dinnerClub.classes.enums.EventStatus;
import com.ates.dinnerClub.entities.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventDTO {
    private String location;
    private Date date;
    private EventStatus status;
    private int themeId;

    public CreateEventDTO(Event event) {
        this.location = event.getLocation();
        this.date = event.getDate();
        this.status = event.getStatus();
        this.themeId = event.getTheme().getId();
    }
}
