package com.erathia.erathiaData.models;

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
    private int id;

    private String name;
    @Column(columnDefinition = "integer default -1")
    private int sourceId;
    private Integer fans;
    @OneToMany(mappedBy = "artist")
    private List<Album> albums;

    public void update(Artist artist) {
        this.name = artist.getName();
        this.fans = artist.getFans();
        this.albums = artist.getAlbums();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sourceId, fans, albums);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Artist artist = (Artist) obj;
        return id == artist.getId() && name.equals(artist.getName()) && sourceId == artist.getSourceId() && fans.equals(artist.getFans()) && albums.equals(artist.getAlbums());
    }
}
