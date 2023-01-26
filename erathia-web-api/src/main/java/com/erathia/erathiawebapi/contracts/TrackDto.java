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
    private int id;
    private Double bpm;
    private int duration;
    private LocalDate releaseDate;
    private String title;
    private Integer trackPosition;
    private Integer album;
}