package com.erathia.erathiadata.repositories;

import com.erathia.erathiadata.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Optional<Artist> findBySourceId(int sourceId);
    Optional<Artist> findByName(String name);
}
