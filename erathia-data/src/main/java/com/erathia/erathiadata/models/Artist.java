package com.erathia.erathiadata.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Column(columnDefinition = "integer default -1")
    private Integer sourceId;
    private Integer fans;
    @OneToMany(mappedBy = "artist")
    private List<Album> albums;

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sourceId, fans, albums);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Artist artist = (Artist) obj;
        return id.equals(artist.getId()) && name.equals(artist.getName()) && sourceId.equals(artist.getSourceId()) && fans.equals(artist.getFans()) && albums.equals(artist.getAlbums());
    }
}
