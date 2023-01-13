package com.erathia.erathiaMusicClient.musicsClient.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistDto {
    private int id;
    private String name;
    private String link;
    @JsonProperty("nb_fan")
    private int fansNumber;
}
