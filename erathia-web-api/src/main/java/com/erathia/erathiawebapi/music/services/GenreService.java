package com.erathia.erathiawebapi.music.services;

import com.erathia.erathiaData.models.Genre;
import com.erathia.erathiaData.repositories.ICatalogData;
import com.erathia.erathiawebapi.contracts.GenreDto;
import com.erathia.erathiawebapi.music.mappers.IMapEntityDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@ComponentScan(basePackages = {"com.erathia.erathiaData"})
public class GenreService implements IService<GenreDto, Genre>{
    private final ICatalogData dataCatalog;
    private final IMapEntityDto<GenreDto, Genre> mapper;

    @Override
    public GenreDto findById(int id) throws EntityNotFoundException {
        var optional = dataCatalog.getGenres().findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return mapper.mapToDto(optional.get());
    }

    public List<GenreDto> findAll() {
        return dataCatalog.getGenres()
                .findAll()
                .stream()
                .map(mapper::mapToDto)
                .toList();
    }

    public void add(GenreDto genreDto) throws EntityExistsException {
        var optional = dataCatalog.getGenres().findByName(genreDto.getName());
        if (optional.isPresent()) {
            throw new EntityExistsException();
        }
        dataCatalog.getGenres().save(mapper.mapToEntity(genreDto));
    }

    public void update(int id, GenreDto genreDto) throws EntityNotFoundException {
        var optional = dataCatalog.getGenres().findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        Genre genre = optional.get();
        genre.update(mapper.mapToEntity(genreDto));
        dataCatalog.getGenres().save(genre);
    }

    @Override
    public void delete(int id, GenreDto genreDto) throws EntityNotFoundException {
        var optional = dataCatalog.getGenres().findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        dataCatalog.getGenres().deleteById(id);
    }

}
