package com.ates.dinnerClub.services;

import com.ates.dinnerClub.classes.dto.theme.CreateThemeDTO;
import com.ates.dinnerClub.classes.dto.theme.ThemeDTO;
import com.ates.dinnerClub.entities.Theme;

import java.util.List;

public interface IThemeService {
    List<ThemeDTO> getAllThemes();

    ThemeDTO getThemeById(int id);

    Theme getThemeByIdForCreation(int id);

    ThemeDTO addTheme(CreateThemeDTO theme);

    ThemeDTO updateTheme(ThemeDTO theme);

    void deleteTheme(int id);
}
