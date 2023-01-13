package com.erathia.erathiamusicupdater;

import com.erathia.erathiamusicupdater.music.updater.IUpdateMusic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ErathiaMusicUpdaterApplication implements CommandLineRunner {

    IUpdateMusic updateMusic;

    @Autowired
    ErathiaMusicUpdaterApplication(IUpdateMusic u) {
        updateMusic=u;
    }

    public static void main(String[] args) {
        SpringApplication.run(ErathiaMusicUpdaterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        updateMusic.updateByArtistName("linkin park");
    }
}
