package com.erathia.erathiawebapi.music.services;

import com.erathia.erathiadata.models.Artist;
import com.erathia.erathiadata.repositories.ICatalogData;
import com.erathia.erathiawebapi.contracts.ArtistDto;
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
public class ArtistService implements IService<ArtistDto, Artist> {
    private static final Logger logger = LoggerFactory.getLogger(ArtistService.class);
    private final ICatalogData dataCatalog;
    private final IMapEntityDto<ArtistDto, Artist> mapper;

    @Override
    public ArtistDto findById(int id) throws EntityNotFoundException{
        logger.info("Run findById(int), id={}",id);

        logger.debug("Send query for artist where id={}",id);
        var optional = dataCatalog.getArtists().findById(id);

        if (optional.isEmpty()) {
            logger.warn("Artist not found in DB");
            throw new EntityNotFoundException();
        }
        var artist = mapper.mapToDto(optional.get());

        logger.info("Finish finById(int)");
        return artist;
    }

    @Override
    public List<ArtistDto> findAll() {
        logger.info("Run findAll()");

        logger.debug("Sand query for all artists");
        return dataCatalog.getArtists()
                .findAll()
                .stream()
                .map(mapper::mapToDto)
                .toList();
    }

    @Override
    public void add(ArtistDto artistDto) throws EntityExistsException {
        logger.info("Run add(ArtistDto), artistDto={}", artistDto);
        Artist artist = mapper.mapToEntity(artistDto);

        logger.debug("Send query for artist where name={}", artistDto.getName());
        var optional = dataCatalog.getArtists().findByName(artistDto.getName());
        if (optional.isPresent()) {
            logger.warn("Artist with name={} found in DB", artistDto.getName());
            throw new EntityExistsException();
        }
        artist.setSourceId(-1);

        logger.debug("Save artist to DB");
        dataCatalog.getArtists().save(artist);

        logger.info("Finish add(ArtistDto)");
    }

    @Override
    public void update(int id, ArtistDto artistDto) throws EntityNotFoundException{
        logger.info("Run update(int,ArtistDto), id={}, artistDto={}",id, artistDto);

        logger.debug("Sand query for artist where id={}",id);
        var optional = dataCatalog.getArtists().findById(id);
        if (optional.isEmpty()) {
            logger.warn("Artist not found in DB");
            throw new EntityNotFoundException();
        }
        Artist artist = optional.get();

        logger.debug("Set new artist properties");
        artist.setName(artistDto.getName());
        artist.setFans(artistDto.getFans());

        logger.debug("save updated artist to DB");
        dataCatalog.getArtists().save(artist);

        logger.info("Finish update(int,ArtistDto)");
    }

    @Override
    public void delete(int id) throws EntityNotFoundException {
        logger.info("Run delete(int, id={}",id);

        logger.debug("Send query for artist where id={}",id);
        var optional = dataCatalog.getArtists().findById(id);
        if (optional.isEmpty()) {
            logger.warn("Artist not found in DB");
            throw new EntityNotFoundException();
        }
        logger.debug("Delete artist from DB");
        dataCatalog.getArtists().deleteById(id);
        logger.info("Finish delete(int)");
    }
}
