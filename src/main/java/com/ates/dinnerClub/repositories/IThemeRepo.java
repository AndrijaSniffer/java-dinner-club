package com.ates.dinnerClub.repositories;

import com.ates.dinnerClub.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IThemeRepo extends JpaRepository<Theme, Integer> {
}
