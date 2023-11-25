package com.macfi.controller;


import com.macfi.model.utils.Statistic;
import com.macfi.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("statistic")
@RestController
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping()//localhost:8080/statistic?idClassroom=1&idStudent=1
    public ResponseEntity<Statistic> getStatistics(@RequestParam Long idClassroom, @RequestParam Long idStudent) {
        try {
            Statistic statistics = statisticService.getStatistics(idStudent, idClassroom);
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    //todo add media by time if needed

}
