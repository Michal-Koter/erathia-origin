package com.erathia.erathiawebapi.music.controllers;

import com.erathia.erathiadata.models.Album;
import com.erathia.erathiawebapi.music.contracts.AlbumDto;
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
@RequestMapping(value = "/api/album")
public class AlbumController {
    private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);
    private final IService<AlbumDto, Album> service;

    @GetMapping( "/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") int id) {
        logger.info("Run findById(int), id={}",id);
        try {
            var album = service.findById(id);
            logger.info("Finish successfully findById(int)");
            return new ResponseEntity<>(album, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.warn("Finish failure findById(int)");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            logger.warn("Finish failure findById(int), exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        logger.info("Run findAll()");
        try {
            var result = service.findAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.warn("Finish failure findAll(), exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody AlbumDto albumDto) {
        logger.info("Run add(AlbumDto), albumDto={}",albumDto);
        try {
            service.add(albumDto);
            logger.info("Finish successfully add(AlbumDto)");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            logger.warn("Finish failure add(AlbumDto)");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (RuntimeException e) {
            logger.warn("Finish failure add(AlbumDto), exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody AlbumDto albumDto, @PathVariable int id) {
        logger.info("Run update(AlbumDto,int), id={}, albumDto={}",id,albumDto);
        try {
            service.update(id, albumDto);
            logger.info("Finish successfully update(AlbumDto,int)");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            logger.warn("Finish failure update(AlbumDto,int)");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            logger.warn("Finish failure update(AlbumDto,int), exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        logger.info("Run delete(int), id={}",id);
        try {
            service.delete(id);
            logger.info("Finish successfully delete(int)");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.warn("Finish failure delete(int)");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            logger.warn("Finish failure delete(int), exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
