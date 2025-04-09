package com.ates.dinnerClub.classes.dto.guest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestInvitationDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean isAccepted;
    private boolean isAttended;

    public GuestInvitationDTO(GuestInvitationProjection guestInvitation) {
        this.id = guestInvitation.getId();
        this.firstName = guestInvitation.getFirstName();
        this.lastName = guestInvitation.getLastName();
        this.email = guestInvitation.getEmail();
        this.phoneNumber = guestInvitation.getPhoneNumber();
        this.isAccepted = guestInvitation.getIsAccepted();
        this.isAttended = guestInvitation.getIsAttended();
    }
}
