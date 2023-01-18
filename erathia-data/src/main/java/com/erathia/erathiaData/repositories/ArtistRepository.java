package com.erathia.erathiaData.repositories;

import com.erathia.erathiaData.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Optional<Artist> findBySourceId(int sourceId);
    Optional<Artist> findByName(String name);
}
