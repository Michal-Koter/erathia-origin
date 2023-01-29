package com.erathia.erathiawebapi.music.mappers;

import com.erathia.erathiadata.models.Album;
import com.erathia.erathiawebapi.contracts.AlbumDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper implements IMapEntityDto<AlbumDto, Album> {
    private static final Logger logger = LoggerFactory.getLogger(AlbumMapper.class);

    @Override
    public AlbumDto mapToDto(Album album) {
        logger.debug("Run mapToDto(Album), album={}", album);
        AlbumDto albumDto = new AlbumDto();
        albumDto.setId(album.getId());
        albumDto.setArtist(album.getArtist().getId());
        albumDto.setFans(album.getFans());
        albumDto.setGenre(album.getGenre().getName());
        albumDto.setTitle(album.getTitle());
        albumDto.setReleaseDate(album.getReleaseDate());
        return albumDto;
    }

    @Override
    public Album mapToEntity(AlbumDto albumDto) {
        logger.debug("Run mapToEntity(AlbumDto), albumDto={}", albumDto);
        Album album = new Album();
        album.setId(albumDto.getId());
        album.setFans(albumDto.getFans());
        album.setReleaseDate(albumDto.getReleaseDate());
        album.setTitle(albumDto.getTitle());
        return album;
    }
}
