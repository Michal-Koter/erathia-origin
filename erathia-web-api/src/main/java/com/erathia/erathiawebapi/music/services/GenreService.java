package com.erathia.erathiawebapi.music.services;

import com.erathia.erathiadata.models.Genre;
import com.erathia.erathiadata.repositories.ICatalogData;
import com.erathia.erathiawebapi.music.contracts.GenreDto;
import com.erathia.erathiawebapi.music.mappers.IMapEntityDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@ComponentScan(basePackages = {"com.erathia.erathiadata"})
public class GenreService implements IService<GenreDto, Genre>{
    private static final Logger logger = LoggerFactory.getLogger(GenreService.class);
    private final ICatalogData dataCatalog;
    private final IMapEntityDto<GenreDto, Genre> mapper;

    @Override
    public GenreDto findById(int id) throws EntityNotFoundException {
        logger.info("Run findById(int), id={}",id);

        logger.debug("Send query for genre where id={}",id);
        var optional = dataCatalog.getGenres().findById(id);
        if (optional.isEmpty()) {
            logger.warn("Genre not found in DB");
            throw new EntityNotFoundException();
        }
        var genre = mapper.mapToDto(optional.get());

        logger.info("Finish finById(int)");
        return genre;
    }

    public List<GenreDto> findAll() {
        logger.info("Run findAll()");

        logger.debug("Sand query for all genres");
        return dataCatalog.getGenres()
                .findAll()
                .stream()
                .map(mapper::mapToDto)
                .toList();
    }

    public void add(GenreDto genreDto) throws EntityExistsException {
        logger.info("Run add(GenreDto), genreDto={}", genreDto);

        logger.debug("Send query for genre where name={}", genreDto.getName());
        var optional = dataCatalog.getGenres().findByName(genreDto.getName());
        if (optional.isPresent()) {
            logger.warn("Similar genre found in DB");
            throw new EntityExistsException();
        }
        Genre genre = mapper.mapToEntity(genreDto);
        genre.setSourceId(-1);

        logger.debug("Save genre to DB");
        dataCatalog.getGenres().save(genre);

        logger.info("Finish add(GenreDto)");
    }

    public void update(int id, GenreDto genreDto) throws EntityNotFoundException {
        logger.info("Run update(int,GenreDto), id={}, genreDto={}",id, genreDto);

        logger.debug("Sand query for genre where id={}", id);
        var optional = dataCatalog.getGenres().findById(id);
        if (optional.isEmpty()) {
            logger.warn("Album not found in DB");
            throw new EntityNotFoundException();
        }
        Genre genre = optional.get();
        logger.debug("Set new genre properties");
        genre.setName(genreDto.getName().toLowerCase());
        logger.debug("Save genre to DB");
        dataCatalog.getGenres().save(genre);

        logger.info("Finish update(int,GenreDto)");
    }

    @Override
    public void delete(int id) throws EntityNotFoundException {
        logger.info("Run delete(int), id={}",id);

        logger.debug("Sand query to genre where id={}",id);
        var optional = dataCatalog.getGenres().findById(id);
        if (optional.isEmpty()) {
            logger.warn("Genre not found in DB");
            throw new EntityNotFoundException();
        }
        logger.debug("Delete album from DB");
        dataCatalog.getGenres().deleteById(id);
        logger.info("Finish delete(int)");
    }

}
