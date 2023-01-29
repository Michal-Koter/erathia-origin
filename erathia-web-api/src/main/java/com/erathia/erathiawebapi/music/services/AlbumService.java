package com.erathia.erathiawebapi.music.services;

import com.erathia.erathiadata.models.*;
import com.erathia.erathiadata.repositories.*;
import com.erathia.erathiawebapi.contracts.*;
import com.erathia.erathiawebapi.music.contracts.AlbumDto;
import com.erathia.erathiawebapi.music.mappers.*;
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
public class AlbumService implements IService<AlbumDto, Album>{
    private static final Logger logger = LoggerFactory.getLogger(AlbumService.class);
    private final ICatalogData dataCatalog;
    private final IMapEntityDto<AlbumDto, Album> albumMapper;

    @Override
    public AlbumDto findById(int id) throws EntityNotFoundException {
        logger.info("Run findById(int), id={}",id);

        logger.debug("Send query for album where id={}",id);
        var optional = dataCatalog.getAlbums().findById(id);

        if (optional.isEmpty()) {
            logger.warn("Album not found in DB");
            throw new EntityNotFoundException();
        }
        var album = albumMapper.mapToDto(optional.get());

        logger.info("Finish finById(int)");
        return album;
    }

    @Override
    public List<AlbumDto> findAll() {
        logger.info("Run findAll()");

        logger.debug("Sand query for all albums");
        return dataCatalog.getAlbums()
                .findAll()
                .stream()
                .map(albumMapper::mapToDto)
                .toList();
    }

    @Override
    public void add(AlbumDto albumDto) throws EntityExistsException {
        logger.info("Run add(AlbumDto), albumDto={}", albumDto);
        Album album = albumMapper.mapToEntity(albumDto);

        logger.debug("Set album artist");
        album.setArtist(
                dataCatalog.getArtists().findById(albumDto.getArtist()).orElse(null)
        );
        logger.debug("Set album genre");
        album.setGenre(
                dataCatalog.getGenres().findByName(albumDto.getGenre()).orElse(null)
        );
        album.setSourceId(-1);

        if (isInDatabase(albumDto)) {
            logger.warn("Similar album found in DB");
            throw new EntityExistsException();
        }

        logger.debug("Save album to DB");
        dataCatalog.getAlbums().save(album);

        logger.info("Finish add(AlbumDto)");
    }

    @Override
    public void update(int id, AlbumDto albumDto) throws EntityNotFoundException{
        logger.info("Run update(int,AlbumDto), id={}, albumDto={}",id, albumDto);

        logger.debug("Sand query for album where id={}",id);
        var optional = dataCatalog.getAlbums().findById(id);
        if (optional.isEmpty()) {
            logger.warn("Album not found in DB");
            throw new EntityNotFoundException();
        }
        Album album = optional.get();

        logger.debug("Set new album properties");
        album.setTitle(albumDto.getTitle());
        album.setFans(albumDto.getFans());
        album.setReleaseDate(albumDto.getReleaseDate());
        album.setArtist(
                dataCatalog.getArtists()
                        .findById(albumDto.getId())
                        .orElse(null)
                );
        album.setGenre(
                dataCatalog.getGenres()
                        .findByName(albumDto.getGenre().toLowerCase())
                        .orElse(null)
        );

        logger.debug("Save updated album to DB");
        dataCatalog.getAlbums().save(album);
        logger.info("Finish update(int,AlbumDto)");
    }

    @Override
    public void delete(int id) throws EntityNotFoundException{
        logger.info("Run delete(int), id={}",id);

        logger.debug("Sand query to album where id={}",id);
        var optional = dataCatalog.getAlbums().findById(id);
        if (optional.isEmpty()) {
            logger.warn("Album not found in DB");
            throw new EntityNotFoundException();
        }
        logger.debug("Delete album from DB");
        dataCatalog.getAlbums().deleteById(id);
        logger.info("Finish delete(int)");
    }

    private boolean isInDatabase(AlbumDto albumDto) {
        logger.debug("Sand query for album where title={}, artistId={}",albumDto.getTitle(),albumDto.getArtist());
        var album = dataCatalog.getAlbums().findByTitleAndAndArtistId(albumDto.getTitle(),albumDto.getArtist());
        return album.isPresent();
    }
}
