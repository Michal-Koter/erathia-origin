package com.erathia.erathiawebapi.music.services;

import com.erathia.erathiaData.models.Track;
import com.erathia.erathiaData.repositories.ICatalogData;
import com.erathia.erathiawebapi.contracts.TrackDto;
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
public class TrackService implements IService<TrackDto, Track> {
    private final ICatalogData dataCatalog;
    private final IMapEntityDto<TrackDto, Track> mapper;

    @Override
    public TrackDto findById(int id) throws EntityNotFoundException{
        var optional = dataCatalog.getTracks().findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return mapper.mapToDto(optional.get());
    }

    @Override
    public List<TrackDto> findAll() {
        return dataCatalog.getTracks()
                .findAll()
                .stream()
                .map(mapper::mapToDto)
                .toList();
    }

    @Override
    public void add(TrackDto trackDto) throws EntityExistsException {
        Track track = new Track();
        track.setReleaseDate(trackDto.getReleaseDate());
        track.setDuration(trackDto.getDuration());
        track.setTitle(trackDto.getTitle());
        track.setBpm(trackDto.getBpm());
        track.setTrackPosition(trackDto.getTrackPosition());
        track.setAlbum(
                dataCatalog.getAlbums().findById(trackDto.getId()).orElse(null)
        );

        var optional = dataCatalog.getTracks().findByTitle(trackDto.getTitle());
        if (optional.isPresent()) {
            throw new EntityExistsException();
        }
        dataCatalog.getTracks().save(mapper.mapToEntity(trackDto));
    }

    @Override
    public void update(int id, TrackDto entityDto) throws EntityNotFoundException{
        var optional = dataCatalog.getTracks().findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        Track track = optional.get();
        track.update(mapper.mapToEntity(entityDto));
        dataCatalog.getTracks().save(track);
    }

    @Override
    public void delete(int id, TrackDto trackDto) throws EntityNotFoundException {
        var optional = dataCatalog.getTracks().findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        dataCatalog.getTracks().deleteById(id);
    }
}
