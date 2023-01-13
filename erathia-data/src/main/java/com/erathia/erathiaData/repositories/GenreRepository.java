package com.erathia.erathiaData.repositories;

import com.erathia.erathiaData.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findBySourceId(int sourceId);
}
