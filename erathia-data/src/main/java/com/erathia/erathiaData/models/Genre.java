package com.erathia.erathiaData.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int sourceId;
    @OneToMany(mappedBy = "genre")
    private List<Album> albums;

    @Override
    public int hashCode() {
        return Objects.hash(id,name,sourceId,albums);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Genre genre = (Genre) obj;
        return id==genre.getId() && name.equals(genre.getName()) && sourceId==genre.getSourceId() && albums.equals(genre.getAlbums());
    }
}
