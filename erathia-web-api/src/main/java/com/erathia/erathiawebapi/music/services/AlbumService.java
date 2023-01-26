package com.erathia.erathiawebapi.music.services;

import com.erathia.erathiaData.models.*;
import com.erathia.erathiaData.repositories.*;
import com.erathia.erathiawebapi.contracts.*;
import com.erathia.erathiawebapi.music.mappers.*;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@ComponentScan(basePackages = {"com.erathia.erathiaData"})
public class AlbumService implements IService<AlbumDto, Album>{
    private final ICatalogData dataCatalog;
    private final IMapEntityDto<AlbumDto, Album> albumMapper;

    @Override
    public AlbumDto findById(int id) throws EntityNotFoundException {
        var optional = dataCatalog.getAlbums().findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return albumMapper.mapToDto(optional.get());
    }

    @Override
    public List<AlbumDto> findAll() {
        return dataCatalog.getAlbums()
                .findAll()
                .stream()
                .map(albumMapper::mapToDto)
                .toList();
    }

    @Override
    public void add(AlbumDto albumDto) throws EntityExistsException {
        Album album = albumMapper.mapToEntity(albumDto);
        album.setArtist(
                dataCatalog.getArtists().findById(albumDto.getArtist()).orElse(null)
        );
        album.setGenre(
                dataCatalog.getGenres().findByName(albumDto.getGenre()).orElse(null)
        );
        album.setSourceId(-1);

        if (isInDatabase(albumDto)) {
            throw new EntityExistsException();
        }

        dataCatalog.getAlbums().save(album);
    }

    @Override
    public void update(int id, AlbumDto albumDto) throws EntityNotFoundException{
        var optional = dataCatalog.getAlbums().findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        Album album = optional.get();

        album.update(albumMapper.mapToEntity(albumDto));
        album.setArtist(
                dataCatalog.getArtists().findById(albumDto.getArtist()).orElse(null)
        );
        dataCatalog.getAlbums().save(album);
    }

    @Override
    public void delete(int id, AlbumDto albumDto) throws EntityNotFoundException{
        var optional = dataCatalog.getAlbums().findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        dataCatalog.getAlbums().deleteById(id);
    }

    private boolean isInDatabase(AlbumDto albumDto) {//poprawić query
        var album = dataCatalog.getAlbums().findByTitleAndAndArtistId(albumDto.getTitle(),albumDto.getArtist());
        return album.isPresent();
    }
}
