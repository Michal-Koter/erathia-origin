package com.erathia.erathiaMusicClient.musicsClient;

import com.erathia.erathiaMusicClient.musicsClient.contract.AlbumDto;
import com.erathia.erathiaMusicClient.musicsClient.contract.ArtistDto;
import com.erathia.erathiaMusicClient.musicsClient.contract.GenreDto;
import com.erathia.erathiaMusicClient.musicsClient.contract.TrackDto;

import java.util.List;

public interface IMusicClient {
    ArtistDto getArtist(String name);
    ArtistDto getArtist(int id);
    List<AlbumDto> getAlbums(int artistId);
    List<TrackDto> getTracks(int albumId);
    List<GenreDto> getGenres();
}
