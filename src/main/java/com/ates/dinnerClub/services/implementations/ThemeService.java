package com.ates.dinnerClub.services.implementations;

import com.ates.dinnerClub.entities.Theme;
import com.ates.dinnerClub.repositories.IThemeRepo;
import com.ates.dinnerClub.services.IThemeService;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService implements IThemeService {
    private final IThemeRepo themeRepo;

    ThemeService(IThemeRepo themeRepo) {
        this.themeRepo = themeRepo;
    }

    @Override
    public Theme addTheme(String name) {
        if (name != null && !name.isEmpty()) {
            Theme theme = new Theme();
            theme.setName(name);
            return this.themeRepo.save(theme);
        }
        return null;
    }

    @Override
    public void deleteTheme(int id) {
        this.themeRepo.deleteById(id);
    }

    @Override
    public Theme updateTheme(@NotNull Theme theme) {
        if (theme.getId() > 0 && theme.getName() != null && !theme.getName().isEmpty()) {
            return this.themeRepo.save(theme);
        }
        return null;
    }

    @Override
    public Theme getThemeById(int id) {
        if (id > 0) {
            return this.themeRepo.findById(id).orElseThrow();
        }
        return null;
    }

    @Override
    public List<Theme> getAllThemes() {
        return this.themeRepo.findAll();
    }
}
