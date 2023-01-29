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
public class AlbumDto {
    private Integer id;
    private Integer fans;
    private LocalDate releaseDate;
    private String title;
    private Integer artist;
    private String genre;
}
