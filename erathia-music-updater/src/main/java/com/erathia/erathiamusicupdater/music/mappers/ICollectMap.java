package com.erathia.erathiamusicupdater.music.mappers;

import com.erathia.erathiaData.models.*;
import com.erathia.erathiaMusicClient.musicsClient.contract.*;

public interface ICollectMap {
    IMap<AlbumDto, Album> getAlbumMapper();
    IMap<ArtistDto, Artist> getArtistMapper();
    IMap<GenreDto, Genre> getGenreMapper();
    IMap<TrackDto, Track> getTrackMapper();
}
