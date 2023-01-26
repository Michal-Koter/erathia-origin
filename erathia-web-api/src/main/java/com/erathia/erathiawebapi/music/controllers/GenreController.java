package com.erathia.erathiawebapi.music.controllers;

import com.erathia.erathiaData.models.Genre;
import com.erathia.erathiawebapi.contracts.GenreDto;
import com.erathia.erathiawebapi.music.services.IService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genre")
public class GenreController {
    private final IService<GenreDto, Genre> service;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        try {
            var result = service.findById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        var result = service.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody GenreDto genreDto) {
        try {
            service.add(genreDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

// dodać GET update, który prześle użytkownikowi żądany obiekt

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody GenreDto genreDto) {
        try {
            service.update(id, genreDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id, @RequestBody GenreDto genreDto) {
        try {
            service.delete(id, genreDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
