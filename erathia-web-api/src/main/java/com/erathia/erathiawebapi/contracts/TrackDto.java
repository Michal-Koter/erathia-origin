package com.erathia.erathiawebapi.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackDto {
    private Integer id;
    private Double bpm;
    private Integer duration;
    private LocalDate releaseDate;
    private String title;
    private Integer trackPosition;
    private Integer album;

    @Override
    public String toString() {
        return "TrackDto{" +
                "id=" + id +
                ", bpm=" + bpm +
                ", duration=" + duration +
                ", releaseDate=" + releaseDate +
                ", title='" + title + '\'' +
                ", trackPosition=" + trackPosition +
                ", album=" + album +
                '}';
    }
}
