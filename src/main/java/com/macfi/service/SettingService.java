package com.macfi.service;


import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.Setting;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.SettingDto;
import com.macfi.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService {

    @Autowired
    private SettingRepository settingRepository;

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

    public SettingDto getSettingByPersonIdentifier(Long identify) {
        return modelMapping.getInstance().mapToDto(settingRepository.findByPersonIdentifier(identify), SettingDto.class);
    }

    public List<SettingDto> getSettings() {
        return settingRepository.findAll().stream().map(setting -> modelMapping.getInstance().mapToDto(setting, SettingDto.class)).toList();
    }
}
