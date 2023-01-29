package com.erathia.erathiamusicclient.musicsClient.contract.pages;

import com.erathia.erathiamusicclient.musicsClient.contract.GenreDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageGenreDto {
    private List<GenreDto> data;
    private int total;
    private String prev;
    private String next;
}
