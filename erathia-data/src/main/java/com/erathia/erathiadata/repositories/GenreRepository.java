package com.erathia.erathiadata.repositories;

import com.erathia.erathiadata.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findBySourceId(int sourceId);
}
