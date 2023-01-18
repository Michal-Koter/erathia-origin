package com.erathia.erathiaMusicClient.musicsClient;

import com.erathia.erathiaMusicClient.musicsClient.contract.*;
import com.erathia.erathiaMusicClient.musicsClient.contract.pages.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class MusicClient implements IMusicClient {
    private RestTemplate restClient;
    private final String baseUrl = "api.deezer.com";

    public MusicClient() {
        restClient = new RestTemplate();
    }

    @Override
    public ArtistDto getArtist(String name) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(baseUrl)
                .pathSegment("search")
                .queryParam("q", "artist:\"" + name + "\"")
                .queryParam("limit", 1)
                .build()
                .toUri();
        PageTrackDto page = restClient.getForObject(uri, PageTrackDto.class);
        if (!page.getData().isEmpty()) {
            TrackDto track = page.getData().get(0);
            return getArtist(track.getArtist().getId());
        } else {
            return null;
        }
    }

    @Override
    public ArtistDto getArtist(int id) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(baseUrl)
                .pathSegment("artist")
                .pathSegment(id + "")
                .build()
                .toUri();
        return restClient.getForObject(uri, ArtistDto.class);
    }

    @Override
    public List<AlbumDto> getAlbums(int artistId) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(baseUrl)
                .pathSegment("artist")
                .pathSegment(artistId + "")
                .pathSegment("albums")
                .build()
                .toUri();
        List<AlbumDto> albums = new ArrayList<>();
        PageAlbumDto page = restClient.getForObject(uri, PageAlbumDto.class);
        while (true) {
            albums.addAll(page.getData());
            if (page.getNext() != null) {
                page = restClient.getForObject(page.getNext(), PageAlbumDto.class);
            } else {
                break;
            }
        }
        return albums;
    }

    @Override
    public List<TrackDto> getTracks(int albumId) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(baseUrl)
                .pathSegment("album")
                .pathSegment(albumId + "")
                .pathSegment("tracks")
                .build()
                .toUri();
        List<TrackDto> tracks = new ArrayList<>();
        PageTrackDto page = restClient.getForObject(uri, PageTrackDto.class);
        while (page != null) {
            tracks.addAll(page.getData());
            if (page.getNext() != null) {
                page = restClient.getForObject(page.getNext(), PageTrackDto.class);
            } else {
                break;
            }
        }
        return tracks;
    }

    @Override
    public TrackDto getTrack(int trackId) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(baseUrl)
                .pathSegment("track")
                .pathSegment(trackId + "")
                .build()
                .toUri();
        return restClient.getForObject(uri, TrackDto.class);
    }

    @Override
    public List<GenreDto> getGenres() {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(baseUrl)
                .pathSegment("genre")
                .build()
                .toUri();
        PageGenreDto page = restClient.getForObject(uri, PageGenreDto.class);
        if (!page.getData().isEmpty()) {
            return page.getData();
        } else {
            return null;
        }
    }
}
