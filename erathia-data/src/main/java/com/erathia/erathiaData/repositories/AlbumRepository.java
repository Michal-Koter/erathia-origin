package com.erathia.erathiaData.repositories;

import com.erathia.erathiaData.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    Optional<Album> findBySourceId(int sourceId);

    Optional<Album> findByTitleAndAndArtistId(String title, Integer artistId);
}
