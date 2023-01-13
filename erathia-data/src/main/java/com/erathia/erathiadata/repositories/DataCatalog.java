package com.erathia.erathiadata.repositories;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Getter
public class DataCatalog implements ICatalogData{
    private final AlbumRepository albums;
    private final ArtistRepository artists;
    private final GenreRepository genres;
    private final TrackRepository tracks;

    @Autowired
    public DataCatalog(AlbumRepository albums, ArtistRepository artists, GenreRepository genres, TrackRepository tracks) {
        this.albums = albums;
        this.artists = artists;
        this.genres = genres;
        this.tracks = tracks;
    }
}
