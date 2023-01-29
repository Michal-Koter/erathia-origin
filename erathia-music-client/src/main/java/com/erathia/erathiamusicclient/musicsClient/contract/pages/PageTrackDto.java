package com.erathia.erathiamusicclient.musicsClient.contract.pages;

import com.erathia.erathiamusicclient.musicsClient.contract.TrackDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageTrackDto {
    private List<TrackDto> data;
    private int total;
    private String prev;
    private String next;

    @Override
    public String toString() {
        return "PageTrackDto{" +
                "data=" + data +
                ", total=" + total +
                ", prev='" + prev + '\'' +
                ", next='" + next + '\'' +
                '}';
    }
}
