package com.erathia.erathiadata.repositories;

import com.erathia.erathiadata.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    Optional<Album> findBySourceId(int sourceId);
}
