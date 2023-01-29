package com.erathia.erathiamusicclient;

import com.erathia.erathiamusicclient.musicsClient.MusicClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ErathiaMusicClientApplication {

    MusicClient musicClient;

    @Autowired
    public ErathiaMusicClientApplication(MusicClient musicClient) {
        this.musicClient = musicClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(ErathiaMusicClientApplication.class, args);
    }

}
