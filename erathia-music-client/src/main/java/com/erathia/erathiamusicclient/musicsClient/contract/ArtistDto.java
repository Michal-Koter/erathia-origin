package com.erathia.erathiamusicclient.musicsClient.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistDto {
    private int id;
    private String name;
    @JsonProperty("nb_fan")
    private int fansNumber;

    @Override
    public String toString() {
        return "ArtistDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fansNumber=" + fansNumber +
                '}';
    }
}
