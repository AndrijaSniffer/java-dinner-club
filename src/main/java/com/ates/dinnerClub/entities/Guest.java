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
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column()
    private String phoneNumber;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Invitation> invitations;
}
