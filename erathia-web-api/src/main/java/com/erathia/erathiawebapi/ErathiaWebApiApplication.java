package com.erathia.erathiawebapi;

import com.erathia.erathiaData.models.Album;
import com.erathia.erathiawebapi.contracts.AlbumDto;
import com.erathia.erathiawebapi.music.services.IService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class ErathiaWebApiApplication implements CommandLineRunner {

    private final IService<AlbumDto, Album> albumService;

    public static void main(String[] args) {
        SpringApplication.run(ErathiaWebApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setFans(30079);
        albumDto.setTitle("Jogftydsdane");
        albumDto.setReleaseDate(LocalDate.parse("2016-10-21"));
        albumDto.setArtist(1);
        albumDto.setGenre("Pop");
        albumService.add(albumDto);
    }
}
