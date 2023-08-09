package com.erathia.erathiamusicclient.musicsClient.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TrackDto {
    private int id;
    private String title;
    private int duration;
    @JsonProperty("track_position")
    private int trackPosition;
    @JsonProperty("release_date")
    private LocalDate releaseDate;
    private double bpm;
    private ArtistDto artist;
    private AlbumDto album;

    @Override
    public String toString() {
        return "TrackDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", trackPosition=" + trackPosition +
                ", releaseDate=" + releaseDate +
                ", bpm=" + bpm +
                ", artistName=" + (artist!=null ? artist.getName() : "null") +
                ", albumTitle=" + (album!=null ? album.getTitle() : "null") +
                '}';
    }
}
