package com.erathia.erathiamusicupdater.music.updater;

import com.erathia.erathiaMusicClient.musicsClient.*;
import com.erathia.erathiaMusicClient.musicsClient.contract.*;
import com.erathia.erathiaData.models.*;
import com.erathia.erathiaData.repositories.*;
import com.erathia.erathiamusicupdater.music.mappers.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"com.erathia.erathiaMusicClient", "com.erathia.erathiaData"})
@RequiredArgsConstructor
public class MusicUpdater implements IUpdateMusic {
    private final ICatalogData dataCatalog;
    private final IMusicClient musicClient;
    private final ICollectMap mapCollector;
    @Override
    public void updateByArtistName(String name) {
        updateGenres();

        name = name.replaceAll("%20"," ");

        ArtistDto artistDto = musicClient.getArtist(name.toLowerCase());
        List<AlbumDto> albumsDto = musicClient.getAlbums(artistDto.getId());

        Artist artist = mapCollector.getArtistMapper().map(artistDto);
        Optional<Artist> optionalArtis = dataCatalog.getArtists().findBySourceId(artist.getSourceId());
        if (optionalArtis.isPresent()) {
            Artist existedArtis = optionalArtis.get();
            existedArtis.update(artist);
            artist = existedArtis;
        }
        dataCatalog.getArtists().save(artist);


        for (var albumDto : albumsDto) {
            Album album;
            try {
                album = mapCollector.getAlbumMapper().map(albumDto);
            } catch (RuntimeException e) {
                // tu będzie loggowanie
                continue;
            }
            album.setArtist(artist);
            album.setGenre(findGenre(albumDto.getGenreId()));
            Optional<Album> optionalAlbum = dataCatalog.getAlbums().findBySourceId(album.getSourceId());
            if (optionalAlbum.isPresent()) {
                Album existedAlbum = optionalAlbum.get();
                existedAlbum.update(album);
                album = existedAlbum;
            }
            dataCatalog.getAlbums().save(album);

            List<TrackDto> tracksDto = musicClient.getTracks(albumDto.getId());
            for (var t : tracksDto) {
                TrackDto trackDto;
                try {
                    trackDto = musicClient.getTrack(t.getId());
                    Track track = mapCollector.getTrackMapper().map(trackDto);
                    track.setAlbum(album);
                    Optional<Track> optionalTrack = dataCatalog.getTracks().findBySourceId(track.getSourceId());
                    if (optionalTrack.isPresent()) {
                        Track existedTrack = optionalTrack.get();
                        existedTrack.update(track);
                        track = existedTrack;
                    }
                    dataCatalog.getTracks().save(track);
                } catch (RuntimeException e) {
                    // tu będzie loggowanie
                    continue;
                }
            }
        }
    }

    @Override
    public void updateGenres() {
        List<GenreDto> genresDto = musicClient.getGenres();
        for (var genreDto : genresDto) {
//            Genre genre = genreMapper.map(genreDto);
            Genre genre = mapCollector.getGenreMapper().map(genreDto);
            var optionalGenre = dataCatalog.getGenres().findBySourceId(genre.getSourceId());
            if (optionalGenre.isEmpty()) {
                dataCatalog.getGenres().save(genre);
            }
        }
    }

    private Genre findGenre(int genreSourceId) {
        var optionalGenre = dataCatalog.getGenres().findBySourceId(genreSourceId);
        return optionalGenre.isPresent() ? optionalGenre.get() : null;
    }
}
