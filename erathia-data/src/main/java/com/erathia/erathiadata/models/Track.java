package com.erathia.erathiadata.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private Integer duration;
    private Integer trackPosition;
    private LocalDate releaseDate;
    private Double bpm;
    @Column(columnDefinition = "integer default -1")
    private Integer sourceId;
    @ManyToOne
    private Album album;

    @Override
    public int hashCode() {
        return Objects.hash(id, title, sourceId, duration, trackPosition, releaseDate, bpm, album);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Track track = (Track) obj;
        return id.equals(track.getId()) && title.equals(track.getTitle()) && sourceId.equals(track.getSourceId()) && duration.equals(track.getDuration()) && trackPosition.equals(track.getTrackPosition())  && releaseDate.equals(track.getReleaseDate()) && bpm.equals(track.getBpm()) && album.equals(track.getAlbum());
    }
}
