package com.ates.dinnerClub.classes.dto.event;

import com.ates.dinnerClub.classes.dto.theme.ThemeDTO;
import com.ates.dinnerClub.classes.enums.EventStatus;
import com.ates.dinnerClub.entities.Event;
import com.ates.dinnerClub.entities.Theme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private int id;
    private String location;
    private Date date;
    private EventStatus status;
    private ThemeDTO theme;

    public EventDTO(Event event) {
        this.id = event.getId();
        this.location = event.getLocation();
        this.date = event.getDate();
        this.status = event.getStatus();
        this.theme = new ThemeDTO(event.getTheme());
    }
}
