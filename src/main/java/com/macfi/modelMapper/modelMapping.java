package com.macfi.modelMapper;

import com.macfi.exception.MappingIllegalException;
import com.macfi.model.person.Person;
import com.macfi.model.person.Professor;
import com.macfi.model.person.Student;
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
    public <TDto, TEntity> Object mapToEntityAbstract(TDto tDto, Class<TEntity> TEntity) {
        Class<?>[] classes = TEntity.getPermittedSubclasses();
        for (Class<?> classi: classes) {
            if (TEntity == classi) {
                return mapToEntity(tDto, classi);
            }
        }
        throw new MappingIllegalException("type must not be a abstract, Did you create your db correctly?");
    }


    public <TDto, TEntity> TEntity mapToEntity(TDto tDto, Class<TEntity> tEntity) {
        if (tDto == null)
            throw new IllegalArgumentException("Dto cannot be null");
        ModelMapper modelMapper = new ModelMapper();
        try {
            return modelMapper.map(tDto, tEntity);
        } catch (Exception e) {
            throw new MappingIllegalException("dto cannot be mapped");
        }

    }

}
