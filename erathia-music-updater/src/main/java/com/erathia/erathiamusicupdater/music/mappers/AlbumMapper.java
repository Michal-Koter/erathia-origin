package com.erathia.erathiamusicupdater.music.mappers;

import com.erathia.erathiaMusicClient.musicsClient.contract.AlbumDto;
import com.erathia.erathiadata.models.Album;

public class AlbumMapper implements IMap{
    public static Album map(AlbumDto albumDto) {
        Album album = new Album();
        album.setDuration(albumDto.getDuration());
        album.setFans(albumDto.getFans());
        album.setLabel(albumDto.getLabel());
        album.setLink(albumDto.getLink());
        album.setReleaseDate(albumDto.getReleaseDate());
        album.setSourceId(albumDto.getId());
        album.setTitle(albumDto.getTitle());
        return album;
    }
}
