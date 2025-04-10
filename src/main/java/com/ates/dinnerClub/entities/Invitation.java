package com.ates.dinnerClub.entities;

import com.ates.dinnerClub.classes.InvitationId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invitation {
    @EmbeddedId
    private InvitationId id;

    @ManyToOne()
    @JoinColumn(name = "guest_id", insertable = false, updatable = false)
    private Guest guest;

    @ManyToOne()
    @JoinColumn(name = "event_id", insertable = false, updatable = false)
    private Event event;

    @Column(nullable = false)
    private boolean isAccepted;

    @Column(nullable = false)
    private boolean isAttended;
}
