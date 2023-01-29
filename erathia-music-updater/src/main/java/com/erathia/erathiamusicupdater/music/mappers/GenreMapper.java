package com.erathia.erathiamusicupdater.music.mappers;

import com.erathia.erathiaMusicClient.musicsClient.contract.GenreDto;
import com.erathia.erathiaData.models.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements IMap<GenreDto, Genre> {
    private static final Logger logger = LoggerFactory.getLogger(GenreMapper.class);

    public Genre map(GenreDto genreDto) {
        logger.debug("Run map(GenreDto) genreDtp={}",genreDto);
        Genre genre = new Genre();
        genre.setName(genreDto.getName());
        genre.setSourceId(genreDto.getId());
        return genre;
    }
}
