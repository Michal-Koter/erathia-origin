package com.erathia.erathiawebapi.music.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDto {
    private Integer id;
    private Integer fans;
    @JsonProperty("release_date")
    private LocalDate releaseDate;
    private String title;
    private Integer artist;
    private String genre;

    @Override
    public String toString() {
        return "AlbumDto{" +
                "id=" + id +
                ", fans=" + fans +
                ", releaseDate=" + releaseDate +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                ", genre='" + genre + '\'' +
                '}';
    }
}
