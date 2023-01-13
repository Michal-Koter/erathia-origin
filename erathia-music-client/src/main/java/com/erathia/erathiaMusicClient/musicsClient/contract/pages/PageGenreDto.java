package com.erathia.erathiaMusicClient.musicsClient.contract.pages;

import com.erathia.erathiaMusicClient.musicsClient.contract.GenreDto;
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
