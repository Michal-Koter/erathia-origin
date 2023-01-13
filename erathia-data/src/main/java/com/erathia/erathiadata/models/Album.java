package com.erathia.erathiadata.models;

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
    private String link;
    private String label;
    private int duration;
    private int fans;
    private LocalDate releaseDate;
    private int sourceId;
    @ManyToOne()
    private Artist artist;
    @OneToMany(mappedBy = "album")
    private List<Track> tracks;
    @ManyToOne
    private Genre genre;

    public void update(Album album) {
        this.title = album.getTitle();
        this.link = album.getLink();
        this.label = album.getLabel();
        this.duration = album.getDuration();
        this.fans = album.getFans();
        this.releaseDate = album.getReleaseDate();
        this.genre = album.getGenre();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,title,link,label,duration,fans,releaseDate,sourceId,artist,tracks,genre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Album album = (Album) obj;
        return id==album.getId() && title.equals(album.getTitle()) && link.equals(album.getLink()) && label.equals(album.getLabel()) && duration==album.getDuration() && releaseDate.equals(album.getReleaseDate()) && sourceId==album.getSourceId() && artist.equals(album.getArtist()) && tracks.equals(album.getTracks()) && genre.equals(album.getGenre());
    }
}
