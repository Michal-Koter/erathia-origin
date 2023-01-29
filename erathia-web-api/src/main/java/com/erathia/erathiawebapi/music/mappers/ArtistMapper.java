package com.erathia.erathiawebapi.music.mappers;

import com.erathia.erathiadata.models.Artist;
import com.erathia.erathiawebapi.contracts.ArtistDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper implements IMapEntityDto<ArtistDto, Artist> {
    private static final Logger logger = LoggerFactory.getLogger(ArtistMapper.class);

    @Override
    public ArtistDto mapToDto(Artist artist) {
        logger.debug("Run mapToDto(Artist), artist={}", artist);
        ArtistDto artistDto = new ArtistDto();
        artistDto.setFans(artist.getFans());
        artistDto.setName(artist.getName());
        artistDto.setId(artist.getId());
        return artistDto;
    }

    @Override
    public Artist mapToEntity(ArtistDto artistDto) {
        logger.debug("Run mapToEntity(ArtistDto), artistDto={}", artistDto);
        Artist artist = new Artist();
        artist.setId(artistDto.getId());
        artist.setFans(artistDto.getFans());
        artist.setName(artistDto.getName());
        return artist;
    }
}
