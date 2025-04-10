package com.ates.dinnerClub.entities;

import com.ates.dinnerClub.classes.enums.EventStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Date date;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "theme_id", nullable = false)
    private Theme theme;

    @OneToMany(mappedBy = "event", cascade = CascadeType.MERGE)
    private Set<Invitation> invitations;
}
