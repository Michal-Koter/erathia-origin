package com.erathia.erathiaMusicClient.musicsClient.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AlbumDto {
    private int id;
    private String title;
    private String link;
    @JsonProperty("genre_id")
    private int genreId;
    private String label;
    private int duration;
    private int fans;
    @JsonProperty("release_date")
    private LocalDate releaseDate;
    private ArtistDto artist;
    private List<TrackDto> tracks;
}
