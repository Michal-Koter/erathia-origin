package com.erathia.erathiawebapi.music.services;

import com.erathia.erathiaData.models.Artist;
import com.erathia.erathiaData.repositories.ICatalogData;
import com.erathia.erathiawebapi.contracts.ArtistDto;
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
public class ArtistService implements IService<ArtistDto, Artist> {
    private final ICatalogData dataCatalog;
    private final IMapEntityDto<ArtistDto, Artist> mapper;

    @Override
    public ArtistDto findById(int id) throws EntityNotFoundException{
        var optional = dataCatalog.getArtists().findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return mapper.mapToDto(optional.get());
    }

    @Override
    public List<ArtistDto> findAll() {
        return dataCatalog.getArtists()
                .findAll()
                .stream()
                .map(mapper::mapToDto)
                .toList();
    }

    @Override
    public void add(ArtistDto artistDto) throws EntityExistsException {
        Artist artist = new Artist();
        artist.setName(artistDto.getName());
        artist.setFans(artistDto.getFans());
        var optional = dataCatalog.getArtists().findByName(artistDto.getName());
        if (optional.isPresent()) {
            throw new EntityExistsException();
        }
        dataCatalog.getArtists().save(artist);
    }

    @Override
    public void update(int id, ArtistDto artistDto) throws EntityNotFoundException{
        var optional = dataCatalog.getArtists().findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        Artist artist = optional.get();
        artist.update(mapper.mapToEntity(artistDto));
        dataCatalog.getArtists().save(artist);
    }

    @Override
    public void delete(int id, ArtistDto artistDto) throws EntityNotFoundException {
        var optional = dataCatalog.getArtists().findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        dataCatalog.getArtists().deleteById(id);
    }
}
