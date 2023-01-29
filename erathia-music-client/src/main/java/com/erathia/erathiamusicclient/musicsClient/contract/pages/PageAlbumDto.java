package com.erathia.erathiamusicclient.musicsClient.contract.pages;

import com.erathia.erathiamusicclient.musicsClient.contract.AlbumDto;
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

    @Override
    public String toString() {
        return "PageAlbumDto{" +
                "data=" + data +
                ", total=" + total +
                ", prev='" + prev + '\'' +
                ", next='" + next + '\'' +
                '}';
    }
}
