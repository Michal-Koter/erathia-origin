package com.erathia.erathiamusicupdater.music.mappers;

import com.erathia.erathiaMusicClient.musicsClient.contract.ArtistDto;
import com.erathia.erathiaData.models.Artist;

public class ArtistMapper implements IMap{
    public static Artist map(ArtistDto artistDto) {
        Artist artist = new Artist();
        artist.setSourceId(artistDto.getId());
        artist.setFans(artistDto.getFansNumber());
        artist.setLink(artistDto.getLink());
        artist.setName(artistDto.getName());
        return artist;
    }
}
