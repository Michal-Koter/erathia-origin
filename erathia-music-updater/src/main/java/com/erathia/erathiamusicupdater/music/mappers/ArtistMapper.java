package com.erathia.erathiamusicupdater.music.mappers;

import com.erathia.erathiamusicclient.musicsClient.contract.ArtistDto;
import com.erathia.erathiadata.models.Artist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper implements IMap<ArtistDto, Artist> {
    private static final Logger logger = LoggerFactory.getLogger(ArtistMapper.class);

    public Artist map(ArtistDto artistDto) {
        logger.debug("Run map(ArtistDto), artistDto={}",artistDto);
        Artist artist = new Artist();
        artist.setSourceId(artistDto.getId());
        artist.setFans(artistDto.getFansNumber());
        artist.setName(artistDto.getName());
        return artist;
    }
}
