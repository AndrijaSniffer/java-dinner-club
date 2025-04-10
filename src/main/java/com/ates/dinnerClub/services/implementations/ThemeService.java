package com.ates.dinnerClub.services.implementations;

import com.ates.dinnerClub.classes.dto.theme.CreateThemeDTO;
import com.ates.dinnerClub.classes.dto.theme.ThemeDTO;
import com.ates.dinnerClub.entities.Theme;
import com.ates.dinnerClub.repositories.IThemeRepo;
import com.ates.dinnerClub.services.IThemeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService implements IThemeService {
    private final IThemeRepo themeRepo;

    ThemeService(IThemeRepo themeRepo) {
        this.themeRepo = themeRepo;
    }

    @Override
    public List<ThemeDTO> getAllThemes() {
        return this.themeRepo.findAll().stream().map(ThemeDTO::new).toList();
    }

    @Override
    public ThemeDTO getThemeById(long id) {
        return this.themeRepo.findById((int) id).map(ThemeDTO::new).orElseThrow();
    }

    @Override
    public Theme getThemeByIdForCreation(long id) {
        if (id > 0) {
            return this.themeRepo.findById((int) id).orElseThrow();
        }
        return null;
    }

    @Override
    public ThemeDTO addTheme(CreateThemeDTO theme) {
        if (theme != null) {
            Theme themeEntity = new Theme();
            themeEntity.setName(theme.getName());

            return new ThemeDTO(this.themeRepo.save(themeEntity));
        } else {
            throw new IllegalArgumentException("Theme is null");
        }
    }

    @Override
    public ThemeDTO updateTheme(ThemeDTO theme) {
        if (theme != null) {
            Theme themeEntity = this.themeRepo.findById((int) theme.getId()).orElseThrow();
            themeEntity.setName(theme.getName());

            return new ThemeDTO(this.themeRepo.save(themeEntity));
        } else {
            throw new IllegalArgumentException("Theme is null");
        }
    }

    @Override
    public void deleteTheme(long id) {
        this.themeRepo.deleteById((int) id);
    }
}
