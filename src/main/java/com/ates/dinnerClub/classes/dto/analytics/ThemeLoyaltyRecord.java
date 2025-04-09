package com.ates.dinnerClub.classes.dto.analytics;

import com.ates.dinnerClub.classes.dto.guest.GuestDTO;

public record ThemeLoyaltyRecord(
    GuestDTO guest,
    String theme
) {}
