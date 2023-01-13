package com.erathia.erathiaMusicClient.musicsClient.contract.pages;

import com.erathia.erathiaMusicClient.musicsClient.contract.AlbumDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageAlbumDto {
    private List<AlbumDto> data;
    private int total;
    private String prev;
    private String next;
}
