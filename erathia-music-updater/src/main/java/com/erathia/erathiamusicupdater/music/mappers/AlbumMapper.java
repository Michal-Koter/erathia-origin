package com.erathia.erathiamusicupdater.music.mappers;

import com.erathia.erathiamusicclient.musicsClient.contract.AlbumDto;
import com.erathia.erathiadata.models.Album;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper implements IMap<AlbumDto, Album> {
    private static final Logger logger = LoggerFactory.getLogger(AlbumMapper.class);

    public Album map(AlbumDto albumDto) {
        logger.debug("Run mpa(AlbumDto), albumDto={}",albumDto);
        Album album = new Album();
        album.setFans(albumDto.getFans());
        album.setReleaseDate(albumDto.getReleaseDate());
        album.setSourceId(albumDto.getId());
        album.setTitle(albumDto.getTitle());
        return album;
    }
}
