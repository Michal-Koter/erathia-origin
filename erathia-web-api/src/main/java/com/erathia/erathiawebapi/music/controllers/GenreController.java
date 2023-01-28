package com.erathia.erathiawebapi.music.controllers;

import com.erathia.erathiaData.models.Genre;
import com.erathia.erathiawebapi.contracts.GenreDto;
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
        }
    }

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        logger.info("Run findAll()");
        var result = service.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
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
        }
    }

// dodać GET update, który prześle użytkownikowi żądany obiekt

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
