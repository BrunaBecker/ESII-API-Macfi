package com.macfi.controller;

import com.macfi.model.Attendance;
import com.macfi.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping //localhost:5173/attendance
    public List<Attendance> getAttendances() {
        return attendanceService.getAttendances();
    }
    @PostMapping//localhost:5173/attendance
    public Attendance createAttendance(@RequestBody Attendance attendance) {
        return attendanceService.createAttendance(attendance);
    }

    @GetMapping("classroom/{id}") //localhost:5173/attendance/classroom/1
    public List<Attendance> getAttendancesByClassroom(@PathVariable("id") Long id) {
        return attendanceService.getAttendancesByClassroom(id);
    }


    @GetMapping("attendanceInClassroomByDate") //localhost:5173/attendance/attendanceInClassroomByDate?classroomid=1&date=2021-06-01
    public List<Attendance> getAttendancesByClassroomAndDate(@RequestParam("classroomid") Long classroomid, @RequestParam("date") Date date) {
        return attendanceService.getAttendancesByClassroomAndDate(classroomid, date);
    }

    @GetMapping("allHappenings") //localhost:5173/attendance/Happening

    public List<Attendance> getAttendancesHappening() {
        return attendanceService.getAttendancesHappening();
    }


    @GetMapping("allHappeningsByClassroom/{id}") //localhost:5173/attendance/HappeningByClassroom/1
    public List<Attendance> getAttendancesHappeningByClassroom(@PathVariable("id") Long id) {
        return attendanceService.getAttendancesHappeningByClassroom(id);
    }

    @GetMapping("HappeningByStudent/{id}") //localhost:5173/attendance/HappeningBytudent/1
    public List<Attendance> getAttendancesHappeningByStudent(@PathVariable("id") Long id) {
        return attendanceService.getAttendancesHappeningByStudent(id);
    }






}
