package com.erathia.erathiamusicupdater.music.mappers;

import com.erathia.erathiaData.models.*;
import com.erathia.erathiaMusicClient.musicsClient.contract.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MapperCatalog implements ICatalogMapper{
    private final IMap<AlbumDto, Album> albumMapper;
    private final IMap<ArtistDto, Artist> artistMapper;
    private final IMap<GenreDto, Genre> genreMapper;
    private final IMap<TrackDto, Track> trackMapper;
}
