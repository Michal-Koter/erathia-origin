package com.erathia.erathiamusicclient.musicsClient.contract;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GenreDto {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "GenreDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
