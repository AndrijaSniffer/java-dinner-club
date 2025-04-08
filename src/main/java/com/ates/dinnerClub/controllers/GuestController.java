package com.ates.dinnerClub.controllers;

import com.ates.dinnerClub.classes.dto.guest.CreateGuestDTO;
import com.ates.dinnerClub.classes.dto.guest.GuestDTO;
import com.ates.dinnerClub.services.IGuestService;
import com.ates.dinnerClub.services.implementations.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("guest")
public class GuestController {
    private final IGuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping()
    public ResponseEntity<List<GuestDTO>> getAllGuests() {
        return ResponseEntity.ok(this.guestService.getAllGuests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDTO> getGuestById(@PathVariable int id) {
        return ResponseEntity.ok(this.guestService.getGuestById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<GuestDTO> getGuestByEmail(@PathVariable String email) {
        return ResponseEntity.ok(this.guestService.getGuestByEmail(email));
    }

    @GetMapping("/name/first/{firstName}")
    public ResponseEntity<List<GuestDTO>> getGuestsByFirstName(@PathVariable String firstName) {
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        return ResponseEntity.ok(this.guestService.getGuestsByFirstName(firstName));
    }

    @GetMapping("/name/last/{lastName}")
    public ResponseEntity<List<GuestDTO>> getGuestsByLastName(@PathVariable String lastName) {
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        return ResponseEntity.ok(this.guestService.getGuestsByLastName(lastName));
    }

    @GetMapping("/name/full/{fullName}")
    public ResponseEntity<GuestDTO> getGuestsByFullName(@PathVariable String fullName) {
        String[] names = fullName.split(" ");

        String firstName = names[0];
        String lastName = names[1];

        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);

        // The code handles both cases where the first name is before the last name and vice versa.
        try {
            return ResponseEntity.ok(this.guestService.getGuestByFirstAndLastName(firstName, lastName));
        } catch (NoSuchElementException e) {
            String tmp = firstName;
            firstName = lastName;
            lastName = tmp;
            return ResponseEntity.ok(this.guestService.getGuestByFirstAndLastName(firstName, lastName));
        }
    }

    @PostMapping()
    public ResponseEntity<GuestDTO> addGuest(@RequestBody CreateGuestDTO guest) {
        guest.setFirstName(guest.getFirstName().substring(0, 1).toUpperCase() + guest.getFirstName().substring(1));
        guest.setLastName(guest.getLastName().substring(0, 1).toUpperCase() + guest.getLastName().substring(1));
        return ResponseEntity.ok(this.guestService.addGuest(guest));
    }

    @PutMapping()
    public ResponseEntity<GuestDTO> updateGuest(@RequestBody GuestDTO guest) {
        return ResponseEntity.ok(this.guestService.updateGuest(guest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable int id) {
        this.guestService.deleteGuest(id);

        return ResponseEntity.noContent().build();
    }
}
