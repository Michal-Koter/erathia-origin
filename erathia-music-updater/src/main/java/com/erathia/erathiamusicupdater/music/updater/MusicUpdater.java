package com.erathia.erathiamusicupdater.music.updater;

import com.erathia.erathiaMusicClient.musicsClient.*;
import com.erathia.erathiaMusicClient.musicsClient.contract.*;
import com.erathia.erathiadata.models.*;
import com.erathia.erathiadata.repositories.*;
import com.erathia.erathiamusicupdater.music.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicUpdater implements IUpdateMusic{
    private final ICatalogData dataCatalog;
    private final IMusicClient musicClient;

    @Autowired
    MusicUpdater(ICatalogData dataCatalog, IMusicClient musicClient) {
        this.dataCatalog = dataCatalog;
        this.musicClient = musicClient;
    }

    @Override
    public void updateByArtistName(String name) {
        updateGenres();

        ArtistDto artistDto = musicClient.getArtist(name.toLowerCase());
        List<AlbumDto> albumsDto = musicClient.getAlbums(artistDto.getId());

        Artist artist = ArtistMapper.map(artistDto);
        Optional<Artist> optionalArtis = dataCatalog.getArtists().findBySourceId(artist.getSourceId());
        if (optionalArtis.isEmpty()) {
            dataCatalog.getArtists().save(artist);
        } else {
            Artist existedArtis = optionalArtis.get();;
            existedArtis.update(artist);
            dataCatalog.getArtists().save(existedArtis);
        }

        Album existedAlbum;
        Optional<Album> optionalAlbum;
        Album album;
        List<TrackDto> tracksDto;
        Track track;
        Track existedTrack;
        Optional<Track> optionalTrack;
        for (var albumDto : albumsDto) {
            album = AlbumMapper.map(albumDto);
            album.setArtist(artist);
            album.setGenre(findGenre(albumDto.getGenreId()));
            optionalAlbum = dataCatalog.getAlbums().findBySourceId(album.getSourceId());
            if (optionalAlbum.isPresent())  {
                existedAlbum = optionalAlbum.get();
                existedAlbum.update(album);
                album = existedAlbum;
            }
            dataCatalog.getAlbums().save(album);

            tracksDto = musicClient.getTracks(albumDto.getId());
            for (var trackDto : tracksDto) {
                track = TrackMapper.map(trackDto);
                track.setAlbum(album);
                optionalTrack = dataCatalog.getTracks().findBySourceId(track.getSourceId());
                if (optionalTrack.isPresent()) {
                    existedTrack = optionalTrack.get();
                    existedTrack.update(track);
                    track = existedTrack;
                }
                dataCatalog.getTracks().save(track);
            }
        }
    }

    @Override
    public void updateGenres() {
        List<GenreDto> genresDto = musicClient.getGenres();
        for (var genreDto : genresDto) {
            Genre genre = GenreMapper.map(genreDto);
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
