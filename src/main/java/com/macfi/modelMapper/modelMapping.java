package com.macfi.modelMapper;

import com.macfi.exception.MappingIllegalException;
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

        if (entity == null)
            throw new IllegalArgumentException("Entity cannot be null");

        ModelMapper modelMapper = new ModelMapper();
        try {
            return modelMapper.map(entity, TDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new MappingIllegalException("Entity cannot be mapped");
        }

    }

    public <TDto, TEntity> TEntity mapToEntity(TDto tDto, Class<TEntity> TEntity) {
        if (tDto == null)
            throw  new IllegalArgumentException("Dto cannot be null");
        ModelMapper modelMapper = new ModelMapper();
        try {
            return modelMapper.map(tDto, TEntity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new MappingIllegalException("Dto cannot be mapped");
        }

    }

}
