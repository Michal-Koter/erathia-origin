package com.erathia.erathiawebapi.music.mappers;

public interface IMapEntityDto<DtoItem, EntityItem> {
    DtoItem mapToDto(EntityItem entityItem);
    EntityItem mapToEntity(DtoItem dtoItem);
}
