package com.erathia.erathiawebapi.music.mappers;

import com.erathia.erathiadata.models.Genre;
import com.erathia.erathiawebapi.contracts.GenreDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements IMapEntityDto<GenreDto, Genre> {
    private static final Logger logger = LoggerFactory.getLogger(GenreMapper.class);

    @Override
    public GenreDto mapToDto(Genre genre) {
        logger.debug("Run mapToDto(Genre), genre={}", genre);
        GenreDto genreDto = new GenreDto();
        genreDto.setId(genre.getId());
        genreDto.setName(genre.getName());
        return genreDto;
    }

    @Override
    public Genre mapToEntity(GenreDto genreDto) {
        logger.debug("Run mapToEntity(GenreDto), genreDto={}",genreDto);
        Genre genre = new Genre();
        genre.setId(genreDto.getId());
        genre.setName(genreDto.getName().toLowerCase());
        return genre;
    }
}
