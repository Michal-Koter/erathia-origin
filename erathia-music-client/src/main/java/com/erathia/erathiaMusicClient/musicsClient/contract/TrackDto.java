package com.erathia.erathiaMusicClient.musicsClient.contract;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TrackDto {
    private int id;
    private String title;
    private String link;
    private int duration;
    @JsonProperty("track_position")
    private int trackPosition;
    private int rank;
    @JsonProperty("release_date")
    private LocalDate releaseDate;
    private double bpm;
    private ArtistDto artist;
    private AlbumDto album;
}
