package com.erathia.erathiawebapi.music.services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface IService<DtoItem, EntityItem> {
    DtoItem findById(int id) throws EntityNotFoundException;
    List<DtoItem> findAll();
    void add(DtoItem dtoItem) throws EntityExistsException;
    void update(int id, DtoItem dtoItem) throws EntityNotFoundException;
    void delete(int id, DtoItem dtoItem) throws EntityNotFoundException;
}
