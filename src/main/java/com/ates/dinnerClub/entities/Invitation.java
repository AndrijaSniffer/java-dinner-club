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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", insertable = false, updatable = false)
    private Guest guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", insertable = false, updatable = false)
    private Event event;

    @Column(nullable = false)
    private boolean isAccepted;

    @Column(nullable = false)
    private boolean isAttended;
}
