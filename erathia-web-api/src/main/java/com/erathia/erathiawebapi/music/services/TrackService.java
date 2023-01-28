package com.erathia.erathiawebapi.music.services;

import com.erathia.erathiaData.models.Track;
import com.erathia.erathiaData.repositories.ICatalogData;
import com.erathia.erathiawebapi.contracts.TrackDto;
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
@ComponentScan(basePackages = {"com.erathia.erathiaData"})
public class TrackService implements IService<TrackDto, Track> {
    private static final Logger logger = LoggerFactory.getLogger(TrackService.class);
    private final ICatalogData dataCatalog;
    private final IMapEntityDto<TrackDto, Track> mapper;

    @Override
    public TrackDto findById(int id) throws EntityNotFoundException{
        logger.info("Run findById(int), id={}",id);

        logger.debug("Send query for track where id={}",id);
        var optional = dataCatalog.getTracks().findById(id);
        if (optional.isEmpty()) {
            logger.warn("Track not found in DB");
            throw new EntityNotFoundException();
        }
        var track = mapper.mapToDto(optional.get());

        logger.info("Finish finById(int)");
        return track;
    }

    @Override
    public List<TrackDto> findAll() {
        logger.info("Run findAll()");

        logger.debug("Sand query for all tracks");
        return dataCatalog.getTracks()
                .findAll()
                .stream()
                .map(mapper::mapToDto)
                .toList();
    }

    @Override
    public void add(TrackDto trackDto) throws EntityExistsException {
        logger.info("Run add(TrackDto), trackDto={}", trackDto);
        Track track = mapper.mapToEntity(trackDto);

        logger.debug("Set track album");
        track.setAlbum(
                dataCatalog.getAlbums().findById(trackDto.getId()).orElse(null)
        );

        logger.debug("Sand query fo track where title={}",trackDto.getTitle());
        var optional = dataCatalog.getTracks().findByTitle(trackDto.getTitle());
        if (optional.isPresent()) {
            logger.warn("Similar Track found in DB");
            throw new EntityExistsException();
        }

        logger.debug("Save track to DB");
        dataCatalog.getTracks().save(track);

        logger.info("Finish add(TrackDto)");
    }

    @Override
    public void update(int id, TrackDto trackDto) throws EntityNotFoundException{
        logger.info("Run update(int,TrackDto), id={}, trackDto={}",id, trackDto);

        logger.debug("Sand query for track where id={}",id);
        var optional = dataCatalog.getTracks().findById(id);
        if (optional.isEmpty()) {
            logger.warn("track not found in DB");
            throw new EntityNotFoundException();
        }
        Track track = optional.get();
        logger.debug("Set new track properties");
        track.update(mapper.mapToEntity(trackDto));
        logger.debug("Save updated album to DB");
        dataCatalog.getTracks().save(track);
        logger.info("Finish update(int,TrackDto)");
    }

    @Override
    public void delete(int id) throws EntityNotFoundException {
        logger.info("Run delete(int), id={}",id);

        logger.debug("Sand query to track where id={}",id);
        var optional = dataCatalog.getTracks().findById(id);
        if (optional.isEmpty()) {
            logger.warn("Track not found in DB");
            throw new EntityNotFoundException();
        }
        logger.debug("Delete track from DB");
        dataCatalog.getTracks().deleteById(id);
        logger.info("Finish delete(int)");
    }
}
