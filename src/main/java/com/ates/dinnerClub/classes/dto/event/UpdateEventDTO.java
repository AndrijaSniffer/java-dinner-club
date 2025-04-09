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
public class UpdateEventDTO {
    private int id;
    private String location;
    private Date date;
    private EventStatus status;
    private int theme;

    public boolean compareToEntity(Event event) {
        return this.getId() == event.getId() && this.getLocation().equals(event.getLocation())
                && this.getDate().equals(event.getDate()) && this.getStatus().equals(event.getStatus())
                && this.getTheme() == event.getTheme().getId();
    }
}
