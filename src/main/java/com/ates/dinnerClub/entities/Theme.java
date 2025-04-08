package com.ates.dinnerClub.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "theme", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Event> events;
}
