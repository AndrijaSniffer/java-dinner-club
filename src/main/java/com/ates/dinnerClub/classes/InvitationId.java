package com.ates.dinnerClub.classes;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitationId implements Serializable {
    private int guest_id;
    private int event_id;
}
