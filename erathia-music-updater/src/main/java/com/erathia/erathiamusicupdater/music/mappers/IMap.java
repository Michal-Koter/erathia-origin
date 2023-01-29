package com.erathia.erathiamusicupdater.music.mappers;

public interface IMap<DtoItem, EntityItem> {
    EntityItem map(DtoItem dtoItem);
}
