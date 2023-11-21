package com.macfi.service;


import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.Setting;
import com.macfi.model.person.Person;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.SettingDto;
import com.macfi.repository.PersonRepository;
import com.macfi.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService {

    @Autowired
    private SettingRepository settingRepository;
    @Autowired
    private PersonRepository personRepository;

    public SettingDto getSetting(Long id) {
        return modelMapping.getInstance().mapToDto(settingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Setting not found")), SettingDto.class);
    }


    public SettingDto createSetting(SettingDto setting) {
        return modelMapping.getInstance().mapToDto(settingRepository.save(modelMapping.getInstance().mapToEntity(setting, Setting.class)), SettingDto.class);
    }


    public SettingDto updateSetting(SettingDto setting) {
        Setting s = modelMapping.getInstance().mapToEntity(modelMapping.getInstance().mapToEntity(setting.getId(), Setting.class), Setting.class);
        return modelMapping.getInstance().mapToDto(settingRepository.save(s), SettingDto.class);
    }

    public SettingDto getSettingByPersonIdentifier(String identify) {
        return modelMapping.getInstance().mapToDto(settingRepository.findByPersonIdentifier(identify), SettingDto.class);
    }

    public List<SettingDto> getSettings() {
        return settingRepository.findAll().stream().map(setting -> modelMapping.getInstance().mapToDto(setting, SettingDto.class)).toList();
    }


    public SettingDto setPerson(Long idSetting, Long idPerson) {
        Setting s = settingRepository.findById(idSetting).orElseThrow(() -> new EntityNotFoundException("Setting not found"));
        Person p = personRepository.findById(idPerson).orElseThrow(() -> new EntityNotFoundException("Person not found"));
        s.setPerson(p);
        return modelMapping.getInstance().mapToDto(settingRepository.save(s), SettingDto.class);
    }

    public SettingDto getSettingByPersonId(Long id) {
        Person p = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found"));
        Setting s = settingRepository.findByPersonId(id).orElseThrow(() -> new EntityNotFoundException("Setting not found"));
        return modelMapping.getInstance().mapToDto(s, SettingDto.class);
    }
}
