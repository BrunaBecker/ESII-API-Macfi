package com.macfi.controller;


import com.macfi.model.Setting;
import com.macfi.service.SettingService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("setting")
public class SettingController {

    @Autowired
    private SettingService settingService;


    @GetMapping
    public Setting getSettings(@PathParam("id") Long id) {
        return settingService.getSetting(id);
    }

    @PostMapping
    public Setting createSetting(@RequestBody Setting setting) {
        return settingService.saveSetting(setting);
    }

    @PutMapping
    public Setting updateSetting(@RequestBody Setting setting) {
        return settingService.updateSetting(setting);
    }

    @GetMapping("/person/{identify}")
    public Setting getSettingByPersonIdentifier(@PathVariable Long identify) {
        return settingService.getSettingByPersonIdentifier(identify);
    }

}
