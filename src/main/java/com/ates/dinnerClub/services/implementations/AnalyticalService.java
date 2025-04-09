package com.ates.dinnerClub.services.implementations;

import com.ates.dinnerClub.classes.dto.analytics.FrequentNoShowOffendersRecord;
import com.ates.dinnerClub.classes.dto.analytics.MostReliableGuestRecord;
import com.ates.dinnerClub.classes.dto.analytics.ThemeLoyaltyRecord;
import com.ates.dinnerClub.classes.dto.analytics.UpcomingEventsWithLowAttendance;
import com.ates.dinnerClub.classes.dto.guest.GuestDTO;
import com.ates.dinnerClub.classes.dto.invitation.InvitationDTO;
import com.ates.dinnerClub.classes.dto.theme.ThemeDTO;
import com.ates.dinnerClub.classes.enums.EventStatus;
import com.ates.dinnerClub.repositories.IAnalyticsRepo;
import com.ates.dinnerClub.services.IAnalyticalService;
import com.ates.dinnerClub.services.IGuestService;
import com.ates.dinnerClub.services.IInvitationService;
import com.ates.dinnerClub.services.IThemeService;
import com.ates.dinnerClub.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AnalyticalService implements IAnalyticalService {
    private final IAnalyticsRepo analyticsRepo;
    private final IInvitationService invitationService;
    private final IThemeService themeService;
    private final IGuestService guestService;

    public AnalyticalService(IAnalyticsRepo analyticsRepo, IInvitationService invitationService,
                             IThemeService themeService, IGuestService guestService) {
        this.analyticsRepo = analyticsRepo;
        this.invitationService = invitationService;
        this.themeService = themeService;
        this.guestService = guestService;
    }

    @Override
    public List<MostReliableGuestRecord> getTop5MostReliableGuests() {
        return this.analyticsRepo.findTop5MostReliableGuests();
    }

    @Override
    public List<FrequentNoShowOffendersRecord> getFrequentNoShowOffenders() {
        return this.analyticsRepo.findFrequentNoShowOffenders();
    }

    @Override
    public List<UpcomingEventsWithLowAttendance> getUpcomingEventsWithLowAttendance() {
        return this.analyticsRepo.findUpcomingEventsWithLowAttendance().stream()
                .map(record -> new UpcomingEventsWithLowAttendance(
                        record.id(),
                        EventStatus.valueOf(record.status()), // Convert String to enum
                        record.location(),
                        record.date(),
                        record.eventAttendance()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Determines theme loyalty for guests by analyzing their invitation and attendance history.
     * A guest is considered loyal to a theme if they have attended all events for that theme.
     *
     * @return A list of {@link ThemeLoyaltyRecord} representing guests with complete theme participation
     */
    @Override
    public List<ThemeLoyaltyRecord> themeLoyalty() {
        List<ThemeLoyaltyRecord> themeLoyaltyRecordList = new ArrayList<>();

        // Guest section
        List<GuestDTO> guests = this.guestService.getAllGuests();
        Map<Integer, List<InvitationDTO>> guestInvitationMap = new HashMap<>();
        for (GuestDTO guest : guests) {
            List<InvitationDTO> guestInvitations = this.invitationService.getInvitationsByGuestIdAndAcceptedAndAttended(guest.getId());
            guestInvitationMap.put(guest.getId(), guestInvitations);
        }

        // Theme section
        List<ThemeDTO> themes = this.themeService.getAllThemes();
        Map<String, List<InvitationDTO>> themeInvitationMap = new HashMap<>();
        for (ThemeDTO theme : themes) {
            List<InvitationDTO> themeInvitations = this.invitationService.getInvitationsByThemeIdAndStatusCOMPLETED(theme.getId());
            themeInvitationMap.put(theme.getName(), themeInvitations);
        }

        // Checking for guest loyalty
        // Start by iterating over guests, so that we know who to add at the end
        // Then get a list of all invitations for this guest, and start iterating over themes
        // Then get a list of all invitations for this theme, and start iterating over them
        // It is very important to use the distinctByKey method from the Util class to ensure that currentCountedRows is only counting for distinct events for that theme
        // The inner inner inner loop is checking if the InvitationDTO of the filtered distinct theme is in the list of invitations for this guest
        // If it is, then we have a match, and we can increment currentCountedRows
        // If currentCountedRows is equal to howManyRows, then we have a complete theme for this guest, and we can add it to the list
        for (GuestDTO guest : guests) {
            List<InvitationDTO> guestInvitations = guestInvitationMap.get(guest.getId());
            for (String themeName : themeInvitationMap.keySet()) {
                List<InvitationDTO> themeInvitations = themeInvitationMap.get(themeName);
                // Filter invitations for this guest and this theme
                List<InvitationDTO> eventThemeInvitations = themeInvitations.stream()
                        .filter(Util.distinctByKey(i -> i.getEvent().getId())).toList();
                int howManyRows = eventThemeInvitations.size();
                int currentCountedRows = 0;
                for (InvitationDTO invitation : themeInvitations) {
                    if (guestInvitations.contains(invitation)) {
                        currentCountedRows++;
                    }
                }
                // Check loyalty after counting all relevant invitations
                if (howManyRows > 0 && currentCountedRows == howManyRows) {
                    log.info("Guest {} has a theme {} loyalty", guest.getFirstName(), themeName);
                    themeLoyaltyRecordList.add(new ThemeLoyaltyRecord(guest, themeName));
                }
            }
        }

        return themeLoyaltyRecordList;
    }
}
