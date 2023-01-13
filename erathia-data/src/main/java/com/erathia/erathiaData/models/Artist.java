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
    private String link;
    private int sourceId;
    private int fans;
    @OneToMany(mappedBy = "artist")
    private List<Album> albums;

    public void update(Artist artist) {
        this.name = artist.getName();
        this.link = artist.getLink();
        this.fans = artist.getFans();
        this.albums = artist.getAlbums();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name,link,sourceId,fans,albums);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Artist artist = (Artist) obj;
        return id==artist.getId() && name.equals(artist.getName()) && link.equals(artist.getLink()) && sourceId==artist.getSourceId() && fans==artist.getFans() && albums.equals(artist.getAlbums());
    }
}
