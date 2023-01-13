package com.erathia.erathiamusicupdater.music.mappers;

import com.erathia.erathiaMusicClient.musicsClient.contract.GenreDto;
import com.erathia.erathiaData.models.Genre;

public class GenreMapper implements IMap{
     public static Genre map(GenreDto genreDto) {
         Genre genre = new Genre();
         genre.setName(genreDto.getName());
         genre.setSourceId(genreDto.getId());
         return genre;
     }
}
