package com.macfi.controller;


import com.macfi.model.Ping;
import com.macfi.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("localhost:3599")
@RestController
@RequestMapping("ping")
public class PingController {

    @Autowired
    private PingService pingService;

    @PostMapping
    public Ping createPing(Ping ping) {
        return pingService.createPing(ping);
    }


}
