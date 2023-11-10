package com.macfi.modelMapper;

import org.modelmapper.ModelMapper;

public final class modelMapping {

    private static modelMapping INSTANCE;
    public ModelMapper modelMapper;

    private modelMapping() {

    }

    public static modelMapping getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new modelMapping();
            INSTANCE.modelMapper = new ModelMapper();
        }
        return INSTANCE;
    }


    public <TDto, TEntity> TDto mapToDto(TEntity entity, Class<TDto> TDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, TDto);
    }

    public <TDto, TEntity> TEntity mapToEntity(TDto tDto, Class<TEntity> TEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(tDto, TEntity);
    }

}
