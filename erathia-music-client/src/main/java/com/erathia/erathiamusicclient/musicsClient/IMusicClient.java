package com.erathia.erathiamusicclient.musicsClient;

import com.erathia.erathiamusicclient.musicsClient.contract.*;

import java.util.List;

public interface IMusicClient {
    ArtistDto getArtist(String name);

    ArtistDto getArtist(int id);

    List<AlbumDto> getAlbums(int artistId);

    List<TrackDto> getTracks(int albumId);

    TrackDto getTrack(int trackId);

    List<GenreDto> getGenres();
}
