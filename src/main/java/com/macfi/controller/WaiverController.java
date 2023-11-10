package com.macfi.controller;

import com.macfi.model.Waiver;
import com.macfi.service.WaiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("waiver")
public class WaiverController {

    @Autowired
    private WaiverService waiverService;


    @PostMapping
    public Waiver createWaiver(@RequestBody Waiver waiver) {
        return waiverService.createWaiver(waiver);
    }

    @GetMapping("byStudentAndClassroom") //http://localhost:8080/waiver/byStudentAndClassroom?idStudent=1&idClassroom=1
    public Waiver getWaiverByStudentAndClassroom(@RequestParam Long idStudent, @RequestParam Long idClassroom) {
        return waiverService.getWaiverByStudentAndClassroom(idStudent, idClassroom);
    }


}
