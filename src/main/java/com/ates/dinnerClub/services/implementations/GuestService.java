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
    public GuestDTO getGuestById(int id) {
        if (id > 0) {
            return this.guestRepo.findById(id).map(GuestDTO::new).orElseThrow();
        }
        return null;
    }

    @Override
    public Guest getGuestByIdForCreation(int id) {
        if (id > 0) {
            return this.guestRepo.findById(id).orElseThrow();
        }
        return null;
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
        if (guest == null || (guest.getFirstName() == null || guest.getFirstName().isEmpty()
                || guest.getLastName() == null || guest.getLastName().isEmpty()
                || guest.getEmail() == null || guest.getEmail().isEmpty()
                || guest.getPhoneNumber() == null || guest.getPhoneNumber().isEmpty())) {
            throw new IllegalArgumentException("Guest data is invalid");
        } else {
            Guest guestEntity = new Guest();
            guestEntity.setFirstName(guest.getFirstName());
            guestEntity.setFirstName(guest.getFirstName());
            guestEntity.setLastName(guest.getLastName());
            guestEntity.setEmail(guest.getEmail());
            guestEntity.setPhoneNumber(guest.getPhoneNumber());
            return new GuestDTO(this.guestRepo.save(guestEntity));
        }
    }

    @Override
    public GuestDTO updateGuest(GuestDTO guest) {
        if (guest == null || (guest.getFirstName() == null || guest.getFirstName().isEmpty()
                || guest.getLastName() == null || guest.getLastName().isEmpty()
                || guest.getEmail() == null || guest.getEmail().isEmpty()
                || guest.getPhoneNumber() == null || guest.getPhoneNumber().isEmpty())) {
            throw new IllegalArgumentException("Guest data is invalid");
        } else {
            Guest guestEntity = this.guestRepo.findById(guest.getId()).orElseThrow();

            guestEntity.setFirstName(guest.getFirstName());
            guestEntity.setLastName(guest.getLastName());
            guestEntity.setEmail(guest.getEmail());
            guestEntity.setPhoneNumber(guest.getPhoneNumber());
            return new GuestDTO(this.guestRepo.save(guestEntity));
        }
    }

    @Override
    public void deleteGuest(int id) {
        if (id > 0) {
            this.guestRepo.deleteById(id);
        }
    }
}
