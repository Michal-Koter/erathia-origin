package com.erathia.erathiamusicupdater.music.mappers;

import com.erathia.erathiaMusicClient.musicsClient.contract.GenreDto;
import com.erathia.erathiaData.models.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements IMap<GenreDto, Genre> {
    public Genre map(GenreDto genreDto) {
        Genre genre = new Genre();
        genre.setName(genreDto.getName());
        genre.setSourceId(genreDto.getId());
        return genre;
    }
}
