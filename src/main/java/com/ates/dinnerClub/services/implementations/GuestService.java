package com.ates.dinnerClub.services.implementations;

import com.ates.dinnerClub.classes.dto.guest.CreateGuestDTO;
import com.ates.dinnerClub.classes.dto.guest.GuestDTO;
import com.ates.dinnerClub.entities.Guest;
import com.ates.dinnerClub.repositories.IGuestRepo;
import com.ates.dinnerClub.services.IGuestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService implements IGuestService {
    IGuestRepo guestRepo;

    public GuestService(IGuestRepo guestRepo) {
        this.guestRepo = guestRepo;
    }

    @Override
    public List<GuestDTO> getAllGuests() {
        return this.guestRepo.findAll().stream().map(GuestDTO::new).toList();
    }

    @Override
    public GuestDTO getGuestById(long id) {
        return this.guestRepo.findById((int) id).map(GuestDTO::new).orElseThrow();
    }

    @Override
    public Guest getGuestByIdForCreation(long id) {
        return this.guestRepo.findById((int) id).orElseThrow();
    }

    @Override
    public GuestDTO getGuestByEmail(String email) {
        return this.guestRepo.findByEmail(email).map(GuestDTO::new).orElseThrow();
    }

    @Override
    public List<GuestDTO> getGuestsByFirstName(String firstName) {
        return this.guestRepo.findByFirstName(firstName).stream().map(GuestDTO::new).toList();
    }

    @Override
    public List<GuestDTO> getGuestsByLastName(String lastName) {
        return this.guestRepo.findByLastName(lastName).stream().map(GuestDTO::new).toList();
    }

    @Override
    public GuestDTO getGuestByFirstAndLastName(String firstName, String lastName) {
        return this.guestRepo.findByFirstNameAndLastName(firstName, lastName).map(GuestDTO::new).orElseThrow();
    }

    @Override
    public GuestDTO addGuest(CreateGuestDTO guest) {
        if (guest != null) {
            Guest guestEntity = new Guest();
            guestEntity.setFirstName(guest.getFirstName());
            guestEntity.setFirstName(guest.getFirstName());
            guestEntity.setLastName(guest.getLastName());
            guestEntity.setEmail(guest.getEmail());
            guestEntity.setPhoneNumber(guest.getPhoneNumber());
            return new GuestDTO(this.guestRepo.save(guestEntity));

        } else {
            throw new IllegalArgumentException("Guest is null");
        }
    }

    @Override
    public GuestDTO updateGuest(GuestDTO guest) {
        if (guest != null) {
            Guest guestEntity = this.guestRepo.findById((int) guest.getId()).orElseThrow();

            guestEntity.setFirstName(guest.getFirstName());
            guestEntity.setLastName(guest.getLastName());
            guestEntity.setEmail(guest.getEmail());
            guestEntity.setPhoneNumber(guest.getPhoneNumber());
            return new GuestDTO(this.guestRepo.save(guestEntity));
        } else {
            throw new IllegalArgumentException("Guest is null");
        }
    }

    @Override
    public void deleteGuest(long id) {
        this.guestRepo.deleteById((int) id);
    }
}
