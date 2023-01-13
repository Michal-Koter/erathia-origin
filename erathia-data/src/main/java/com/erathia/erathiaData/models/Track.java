package com.erathia.erathiaData.models;

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
    private int id;

    private String title;
    private String link;
    private int duration;
    private int trackPosition;
    @Column(name = "`rank`")
    private int rank;
    private LocalDate releaseDate;
    private double bpm;
    private int sourceId;
    @ManyToOne
    private Album album;

    public void update(Track track) {
        this.title = track.getTitle();
        this.link = track.getLink();
        this.duration = track.getDuration();
        this.trackPosition = track.getTrackPosition();
        this.rank = track.getRank();
        this.releaseDate = track.releaseDate;
        this.bpm = track.getBpm();
        this.album = track.getAlbum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,title,sourceId,link,duration,trackPosition,rank,releaseDate,bpm,album);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Track track = (Track) obj;
        return id==track.getId() && title.equals(track.getTitle()) && sourceId==track.getSourceId() && link.equals(track.getLink()) && duration==track.getDuration() && trackPosition==track.getTrackPosition() && rank==track.getRank() && releaseDate.equals(track.getReleaseDate()) && bpm==track.getBpm() && album.equals(track.getAlbum());
    }
}
