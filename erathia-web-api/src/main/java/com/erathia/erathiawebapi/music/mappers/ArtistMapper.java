package com.erathia.erathiawebapi.music.mappers;

import com.erathia.erathiaData.models.Artist;
import com.erathia.erathiawebapi.contracts.ArtistDto;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper implements IMapEntityDto<ArtistDto, Artist> {
    @Override
    public ArtistDto mapToDto(Artist artist) {
        ArtistDto artistDto = new ArtistDto();
        artistDto.setFans(artist.getFans());
        artistDto.setName(artist.getName());
        artistDto.setId(artist.getId());
        return artistDto;
    }

    @Override
    public Artist mapToEntity(ArtistDto artistDto) {
        Artist artist = new Artist();
        if ((Integer) artistDto.getId() != null) {
            artist.setSourceId(artistDto.getId());
        } else {
            artist.setSourceId(-1);
        }
        artist.setFans(artistDto.getFans());
        artist.setName(artistDto.getName());
        return artist;
    }
}
