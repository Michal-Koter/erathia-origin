package com.erathia.erathiamusicclient.musicsClient;

import com.erathia.erathiamusicclient.musicsClient.contract.*;
import com.erathia.erathiamusicclient.musicsClient.contract.pages.*;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(MusicClient.class);
    private RestTemplate restClient;
    private final String baseUrl = "api.deezer.com";

    public MusicClient() {
        restClient = new RestTemplate();
    }

    @Override
    public ArtistDto getArtist(String name) {
        logger.info("Run getArtist(String), name={}", name);
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(baseUrl)
                .pathSegment("search")
                .queryParam("q", "artist:\"" + name + "\"")
                .queryParam("limit", 1)
                .build()
                .toUri();
        logger.debug("Send request: {}", uri);
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
        logger.info("Run getArtist(int), id={}", id);
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(baseUrl)
                .pathSegment("artist")
                .pathSegment(id + "")
                .build()
                .toUri();
        logger.debug("Send request: {}", uri);
        return restClient.getForObject(uri, ArtistDto.class);
    }

    @Override
    public List<AlbumDto> getAlbums(int artistId) {
        logger.info("Run getAlbums(int), artistId={}",artistId);
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(baseUrl)
                .pathSegment("artist")
                .pathSegment(artistId + "")
                .pathSegment("albums")
                .build()
                .toUri();
        List<AlbumDto> albums = new ArrayList<>();
        logger.debug("Send request: {}", uri);
        PageAlbumDto page = restClient.getForObject(uri, PageAlbumDto.class);
        while (true) {
            albums.addAll(page.getData());
            if (page.getNext() != null) {
                logger.debug("Send request: {}", page.getNext());
                page = restClient.getForObject(page.getNext(), PageAlbumDto.class);
            } else {
                break;
            }
        }
        return albums;
    }

    @Override
    public List<TrackDto> getTracks(int albumId) {
        logger.info("Run getTracks(int), albumId={}",albumId);
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(baseUrl)
                .pathSegment("album")
                .pathSegment(albumId + "")
                .pathSegment("tracks")
                .build()
                .toUri();
        List<TrackDto> tracks = new ArrayList<>();
        logger.debug("Send request: {}", uri);
        PageTrackDto page = restClient.getForObject(uri, PageTrackDto.class);
        while (page != null) {
            tracks.addAll(page.getData());
            if (page.getNext() != null) {
                logger.debug("Send request: {}", page.getNext());
                page = restClient.getForObject(page.getNext(), PageTrackDto.class);
            } else {
                break;
            }
        }
        return tracks;
    }

    @Override
    public TrackDto getTrack(int trackId) {
        logger.info("Run getTrack(int), trackId={}", trackId);
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(baseUrl)
                .pathSegment("track")
                .pathSegment(trackId + "")
                .build()
                .toUri();
        logger.debug("Send request: {}", uri);
        return restClient.getForObject(uri, TrackDto.class);
    }

    @Override
    public List<GenreDto> getGenres() {
        logger.info("Run getGenres()");
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(baseUrl)
                .pathSegment("genre")
                .build()
                .toUri();
        logger.debug("Send request: {}", uri);
        PageGenreDto page = restClient.getForObject(uri, PageGenreDto.class);
        if (!page.getData().isEmpty()) {
            return page.getData();
        } else {
            return null;
        }
    }
}
