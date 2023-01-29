package com.erathia.erathiadata.repositories;

public interface ICatalogData {
    AlbumRepository getAlbums();

    ArtistRepository getArtists();

    GenreRepository getGenres();

    TrackRepository getTracks();
}
