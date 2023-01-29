package com.erathia.erathiamusicupdater.music.mappers;

import com.erathia.erathiamusicclient.musicsClient.contract.GenreDto;
import com.erathia.erathiadata.models.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements IMap<GenreDto, Genre> {
    private static final Logger logger = LoggerFactory.getLogger(GenreMapper.class);

    public Genre map(GenreDto genreDto) {
        logger.debug("Run map(GenreDto) genreDtp={}",genreDto);
        Genre genre = new Genre();
        genre.setName(genreDto.getName().toLowerCase());
        genre.setSourceId(genreDto.getId());
        return genre;
    }
}
