package com.erathia.erathiaData.repositories;

import com.erathia.erathiaData.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TrackRepository extends JpaRepository<Track, Integer> {
    Optional<Track> findBySourceId(int sourceId);
    Optional<Track> findByTitle(String title);
}
