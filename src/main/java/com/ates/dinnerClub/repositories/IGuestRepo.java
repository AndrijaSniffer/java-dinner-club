package com.ates.dinnerClub.repositories;

import com.ates.dinnerClub.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IGuestRepo extends JpaRepository<Guest, Integer> {
    Optional<Guest> findByEmail(String email);

    List<Guest> findByFirstName(String firstName);

    List<Guest> findByLastName(String lastName);

    Optional<Guest> findByFirstNameAndLastName(String firstName, String lastName);
}
