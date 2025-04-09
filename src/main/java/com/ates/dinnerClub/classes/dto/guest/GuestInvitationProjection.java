package com.ates.dinnerClub.classes.dto.guest;

public interface GuestInvitationProjection {
    int getId();

    String getFirstName();

    String getLastName();

    String getEmail();

    String getPhoneNumber();

    // Must be named *get*IsAccepted
    boolean getIsAccepted();

    // Must be named *get*IsAttended
    boolean getIsAttended();
}
