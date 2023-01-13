package com.erathia.erathiaMusicClient.musicsClient.contract.pages;

import com.erathia.erathiaMusicClient.musicsClient.contract.TrackDto;
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
}
