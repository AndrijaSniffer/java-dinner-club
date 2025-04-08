package com.ates.dinnerClub.classes.dto.guest;

import com.ates.dinnerClub.entities.Guest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public GuestDTO(Guest guest) {
        this.id = guest.getId();
        this.firstName = guest.getFirstName();
        this.lastName = guest.getLastName();
        this.email = guest.getEmail();
        this.phoneNumber = guest.getPhoneNumber();
    }
}
