package com.macfi.controller;

import com.macfi.model.Attendance;
import com.macfi.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3599")
@RestController
@RequestMapping("attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping //localhost:3599/attendance
    public List<Attendance> getAttendances() {
        return attendanceService.getAttendances();
    }

    @PostMapping//localhost:3599/attendance
    public Attendance createAttendance(@RequestBody Attendance attendance) {
        return attendanceService.createAttendance(attendance);
    }

    @GetMapping("classroom/{id}") //localhost:3599/attendance/classroom/1
    public List<Attendance> getAttendancesByClassroom(@PathVariable("id") Long id) {
        return attendanceService.getAttendancesByClassroom(id);
    }

    //yyyy-MM-dd hh:mm:ss

    @GetMapping("inClassroomByDate") //localhost:3599/attendance/inClassroomByDate?classroomid=1&date=2021-06-01
    public List<Attendance> getAttendancesByClassroomAndDate(@RequestParam("classroomid") Long classroomid, @RequestParam("date") String date) {
        return attendanceService.getAttendancesByClassroomAndDate(classroomid, date);
    }

    @GetMapping("happening") //localhost:3599/attendance/happenings

    public List<Attendance> getAttendancesHappening() {
        return attendanceService.getAttendancesHappening();
    }


    @GetMapping("happeningByClassroom/{id}") //localhost:3599/attendance/happeningByClassroom/1
    public List<Attendance> getAttendancesHappeningByClassroom(@PathVariable("id") Long id) {
        return attendanceService.getAttendancesHappeningByClassroom(id);
    }

    @GetMapping("happeningByStudent/{id}") //localhost:3599/attendance/happeningByStudent/1
    public List<Attendance> getAttendancesHappeningByStudent(@PathVariable("id") Long id) {
        return attendanceService.getAttendancesHappeningByStudent(id);
    }


}
