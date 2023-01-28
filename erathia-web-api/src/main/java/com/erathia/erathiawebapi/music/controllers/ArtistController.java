package com.erathia.erathiawebapi.music.controllers;

import com.erathia.erathiaData.models.Artist;
import com.erathia.erathiawebapi.contracts.ArtistDto;
import com.erathia.erathiawebapi.music.services.IService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/artist")
public class ArtistController {
    private static final Logger logger = LoggerFactory.getLogger(ArtistController.class);
    private final IService<ArtistDto, Artist> service;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        logger.trace("Run findById(int), id={}",id);
        try {
            var result = service.findById(id);
            logger.info("Finish successfully findById(int)");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            logger.warn("Finish failure findById(int)");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        logger.info("Run findAll()");
        var result = service.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody ArtistDto artistDto) {
        logger.info("Run add(ArtistDto), artistDto={}",artistDto);
        try {
            service.add(artistDto);
            logger.info("Finish successfully add(ArtistDto)");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            logger.warn("Finish failure add(ArtistDto)");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

// dodać GET update, który prześle użytkownikowi żądany obiekt

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody ArtistDto artistDto) {
        logger.info("Run update(int, ArtistDto), id={}, artistDto={}",id,artistDto);
        try {
            service.update(id, artistDto);
            logger.info("Finish successfully update(int, ArtistDto)");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            logger.warn("Finish failure update(int,ArtistDto)");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        logger.info("Run delete(int), id={}",id);
        try {
            service.delete(id);
            logger.info("Finish successfully delete(int)");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.warn("Finish failure delete(int)");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
