package com.erathia.erathiawebapi.music.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDto {
    private Integer id;
    private Integer fans;
    private String name;

    @Override
    public String toString() {
        return "ArtistDto{" +
                "id=" + id +
                ", fans=" + fans +
                ", name='" + name + '\'' +
                '}';
    }
}
