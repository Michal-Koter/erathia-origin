package com.erathia.erathiamusicupdater.music.updater;

import com.erathia.erathiamusicclient.musicsClient.*;
import com.erathia.erathiamusicclient.musicsClient.contract.*;
import com.erathia.erathiadata.models.*;
import com.erathia.erathiadata.repositories.*;
import com.erathia.erathiamusicupdater.music.mappers.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"com.erathia.erathiamusicclient", "com.erathia.erathiadata"})
@RequiredArgsConstructor
public class MusicUpdater implements IUpdateMusic {
    private static final Logger logger = LoggerFactory.getLogger(MusicUpdater.class);
    private final ICatalogData dataCatalog;
    private final IMusicClient musicClient;
    private final ICollectMap mapCollector;
    @Override
    public void updateByArtistName(String name) {
        logger.info("Run updateByArtistName(String), name={}",name);

        updateGenres();

        name = name.replaceAll("%20"," ");

        logger.debug("Send request to MusicClient for artistDto, name={}",name);
        ArtistDto artistDto = musicClient.getArtist(name.toLowerCase());

        logger.debug("Send request to MusicClient for artist's albumsDto, id={}",artistDto.getId());
        List<AlbumDto> albumsDto = musicClient.getAlbums(artistDto.getId());

        Artist artist = mapCollector.getArtistMapper().map(artistDto);

        logger.debug("Send query for artist where sourceId={}", artist.getSourceId());
        Optional<Artist> optionalArtis = dataCatalog.getArtists().findBySourceId(artist.getSourceId());
        if (optionalArtis.isPresent()) {
            Artist existedArtis = optionalArtis.get();

            logger.debug("Set new artist properties");
            existedArtis.update(artist);
            artist = existedArtis;
        }
        logger.debug("Save artist to DB");
        dataCatalog.getArtists().save(artist);


        for (var albumDto : albumsDto) {
            Album album;
            try {
                album = mapCollector.getAlbumMapper().map(albumDto);
            } catch (RuntimeException e) {
                logger.warn("Mapping AlbumDto to Album failure, exception={}",e.getMessage());
                continue;
            }
            album.setArtist(artist);
            album.setGenre(findGenre(albumDto.getGenreId()));

            logger.debug("Send query for album where sourceId={}",album.getSourceId());
            Optional<Album> optionalAlbum = dataCatalog.getAlbums().findBySourceId(album.getSourceId());
            if (optionalAlbum.isPresent()) {
                Album existedAlbum = optionalAlbum.get();

                logger.debug("Set new album properties");
                existedAlbum.update(album);
                album = existedAlbum;
            }
            logger.debug("Save album to DB");
            dataCatalog.getAlbums().save(album);

            logger.debug("Send request to MusicClient for album's tracks, albumId={}",albumDto.getId());
            List<TrackDto> tracksDto = musicClient.getTracks(albumDto.getId());
            for (var t : tracksDto) {
                Track track;
                try {
                    TrackDto trackDto = musicClient.getTrack(t.getId());
                    track = mapCollector.getTrackMapper().map(trackDto);
                } catch (RuntimeException e) {
                    logger.warn("Mapping TrackDto to Track failure, exception={}",e.getMessage());
                    continue;
                }
                track.setAlbum(album);

                logger.debug("Send query for track where sourceId={}",track.getSourceId());
                Optional<Track> optionalTrack = dataCatalog.getTracks().findBySourceId(track.getSourceId());
                if (optionalTrack.isPresent()) {
                    Track existedTrack = optionalTrack.get();

                    logger.debug("Set new track properties");
                    existedTrack.update(track);
                    track = existedTrack;
                }
                logger.debug("Save track to DB");
                dataCatalog.getTracks().save(track);
            }
        }
    }

    @Override
    public void updateGenres() {
        logger.info("Run updateGenres()");

        logger.debug("Send request to MusicClient for genreDto");
        List<GenreDto> genresDto = musicClient.getGenres();
        Genre genre;
        for (var genreDto : genresDto) {
            try {
               genre = mapCollector.getGenreMapper().map(genreDto);
            } catch (RuntimeException e) {
                logger.warn("Mapping GenreDto to Genre failure, exception={}",e.getMessage());
                continue;
            }

            logger.debug("Send query for genre where sourceId={}", genre.getSourceId());
            var optionalGenre = dataCatalog.getGenres().findBySourceId(genre.getSourceId());
            if (optionalGenre.isEmpty()) {
                logger.debug("Save genre to DB");
                dataCatalog.getGenres().save(genre);
            }
        }
    }

    private Genre findGenre(int genreSourceId) {
        var optionalGenre = dataCatalog.getGenres().findBySourceId(genreSourceId);
        return optionalGenre.isPresent() ? optionalGenre.get() : null;
    }
}
