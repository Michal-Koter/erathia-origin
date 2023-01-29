package com.erathia.erathiamusicupdater.music.mappers;

import com.erathia.erathiadata.models.*;
import com.erathia.erathiamusicclient.musicsClient.contract.*;

public interface ICollectMap {
    IMap<AlbumDto, Album> getAlbumMapper();
    IMap<ArtistDto, Artist> getArtistMapper();
    IMap<GenreDto, Genre> getGenreMapper();
    IMap<TrackDto, Track> getTrackMapper();
}
