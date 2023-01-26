package com.erathia.erathiawebapi.music.mappers;

import com.erathia.erathiaData.models.Album;
import com.erathia.erathiawebapi.contracts.AlbumDto;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper implements IMapEntityDto<AlbumDto, Album> {
    @Override
    public AlbumDto mapToDto(Album album) {
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
        Album album = new Album();
        if (albumDto.getId() != null) {
            album.setId(albumDto.getId());
        }
        album.setFans(albumDto.getFans());
        album.setReleaseDate(albumDto.getReleaseDate());
        album.setTitle(albumDto.getTitle());
        return album;
    }
}
