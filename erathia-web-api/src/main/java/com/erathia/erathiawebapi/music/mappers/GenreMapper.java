package com.erathia.erathiawebapi.music.mappers;

import com.erathia.erathiaData.models.Genre;
import com.erathia.erathiawebapi.contracts.GenreDto;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements IMapEntityDto<GenreDto, Genre> {
    @Override
    public GenreDto mapToDto(Genre genre) {
        GenreDto genreDto = new GenreDto();
        genreDto.setId(genre.getId());
        genreDto.setName(genre.getName());
        return genreDto;
    }

    @Override
    public Genre mapToEntity(GenreDto genreDto) {
        Genre genre = new Genre();
        genre.setId(genreDto.getId());
        genre.setName(genreDto.getName());
        return null;
    }
}
