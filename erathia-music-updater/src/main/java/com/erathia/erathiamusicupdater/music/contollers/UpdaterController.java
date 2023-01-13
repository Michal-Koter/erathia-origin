package com.erathia.erathiamusicupdater.music.contollers;

import com.erathia.erathiamusicupdater.music.updater.IUpdateMusic;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UpdaterController {
    private final IUpdateMusic musicUpdater;

    @GetMapping(value = "update/artist")
    public ResponseEntity<Object> updateByArtistName(@RequestParam String name) {
        musicUpdater.updateByArtistName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
