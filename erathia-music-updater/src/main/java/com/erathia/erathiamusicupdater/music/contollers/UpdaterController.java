package com.erathia.erathiamusicupdater.music.contollers;

import com.erathia.erathiamusicupdater.music.updater.IUpdateMusic;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UpdaterController {
    private static final Logger logger = LoggerFactory.getLogger(UpdaterController.class);
    private final IUpdateMusic musicUpdater;

    @GetMapping(value = "update/artist")
    public ResponseEntity<Object> updateByArtistName(@RequestParam String name) {
        logger.info("Run updateByArtistName(String), name={}",name);
        try {
            musicUpdater.updateByArtistName(name);
            logger.info("Finish successfully updateByArtistName(String)");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.warn("Finish failure updateByArtistName(String), exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
