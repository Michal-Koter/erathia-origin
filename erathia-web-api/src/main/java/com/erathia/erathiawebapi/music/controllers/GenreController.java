package com.erathia.erathiawebapi.music.controllers;

import com.erathia.erathiadata.models.Genre;
import com.erathia.erathiawebapi.music.contracts.GenreDto;
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
@RequestMapping("/api/genre")
public class GenreController {
    private static final Logger logger = LoggerFactory.getLogger(GenreController.class);
    private final IService<GenreDto, Genre> service;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        logger.trace("Run findById(id), id={}",id);
        try {
            var result = service.findById(id);
            logger.info("Finish successfully findById(id)");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            logger.warn("Finish failure findById(id)");
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
    public ResponseEntity<Object> add(@RequestBody GenreDto genreDto) {
        logger.info("Run add(GenreDto), genreDto={}",genreDto);
        try {
            service.add(genreDto);
            logger.info("Finish successfully add(GenreDto)");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            logger.warn("Finish failure add(GenreDto)");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (RuntimeException e) {
            logger.warn("Finish failure add(GenreDto), exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody GenreDto genreDto) {
        logger.info("Run update(id, GenreDto), id={}, genreDto={}",id,genreDto);
        try {
            service.update(id, genreDto);
            logger.info("Finish successfully update(id, GenreDto)");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            logger.warn("Finish failure update(id, GenreDto)");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            logger.warn("Finish failure update(id, GenreDto), exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
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
        } catch (RuntimeException e) {
            logger.warn("Finish failure delete(int), exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
