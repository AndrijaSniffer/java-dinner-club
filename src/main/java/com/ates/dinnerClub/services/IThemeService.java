package com.ates.dinnerClub.services;

import com.ates.dinnerClub.entities.Theme;

import java.util.List;

public interface IThemeService {
    Theme addTheme(String name);

    void deleteTheme(int id);

    Theme updateTheme(Theme theme);

    Theme getThemeById(int id);

    List<Theme> getAllThemes();
}
