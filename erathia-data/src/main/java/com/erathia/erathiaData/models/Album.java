package com.erathia.erathiaData.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private Integer fans;
    private LocalDate releaseDate;
    @Column(columnDefinition = "integer default -1")
    private int sourceId;
    @ManyToOne()
    private Artist artist;
    @OneToMany(mappedBy = "album")
    private List<Track> tracks;
    @ManyToOne
    private Genre genre;

    public void update(Album album) {
        this.title = album.getTitle();
        this.fans = album.getFans();
        this.releaseDate = album.getReleaseDate();
        this.genre = album.getGenre();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, fans, releaseDate, sourceId, artist, tracks, genre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Album album = (Album) obj;
        return id == album.getId() && title.equals(album.getTitle()) && releaseDate.equals(album.getReleaseDate()) && sourceId == album.getSourceId() && artist.equals(album.getArtist()) && tracks.equals(album.getTracks()) && genre.equals(album.getGenre());
    }
}
