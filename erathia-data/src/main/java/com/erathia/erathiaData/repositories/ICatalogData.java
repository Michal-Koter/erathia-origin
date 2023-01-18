package com.erathia.erathiaData.repositories;

public interface ICatalogData {
    AlbumRepository getAlbums();

    ArtistRepository getArtists();

    GenreRepository getGenres();

    TrackRepository getTracks();
}
