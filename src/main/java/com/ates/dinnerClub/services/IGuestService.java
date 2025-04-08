package com.ates.dinnerClub.services;

import com.ates.dinnerClub.classes.dto.guest.CreateGuestDTO;
import com.ates.dinnerClub.classes.dto.guest.GuestDTO;
import com.ates.dinnerClub.entities.Guest;

import java.util.List;

public interface IGuestService {
    List<GuestDTO> getAllGuests();

    GuestDTO getGuestById(int id);

    Guest getGuestByIdForCreation(int id);

    GuestDTO getGuestByEmail(String email);

    List<GuestDTO> getGuestsByFirstName(String firstName);

    List<GuestDTO> getGuestsByLastName(String lastName);

    GuestDTO getGuestByFirstAndLastName(String firstName, String lastName);

    GuestDTO addGuest(CreateGuestDTO guest);

    GuestDTO updateGuest(GuestDTO guest);

    void deleteGuest(int id);
}
