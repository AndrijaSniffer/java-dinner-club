package com.ates.dinnerClub.classes.dto.guest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGuestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
