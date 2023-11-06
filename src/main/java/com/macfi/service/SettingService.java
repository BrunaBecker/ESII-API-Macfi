package com.macfi.service;


import com.macfi.model.Setting;
import com.macfi.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

    @Autowired
    private SettingRepository settingRepository;

    public Setting getSetting(Long id) {
        return settingRepository.findById(id).orElse(null);
    }


    public Setting saveSetting(Setting setting) {
        return settingRepository.save(setting);
    }


    public Setting updateSetting(Setting setting) {
        return settingRepository.save(setting);
    }

    public Setting getSettingByPersonIdentifier(Long identify) {
        return settingRepository.findByPersonIdentifier(identify);
    }

}
