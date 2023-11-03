package com.macfi.controller;

import com.macfi.model.AttendanceStatus;
import com.macfi.service.AttendanceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("attendanceStatus")
public class AttendanceStatusController {

    @Autowired
    private AttendanceStatusService attendanceStatusService;

    @GetMapping //localhost:5173/attendanceStatus
    public List<AttendanceStatus> getAttendanceStatus() {
        return attendanceStatusService.getAttendanceStatus();
    }

    @PostMapping //localhost:5173/attendanceStatus
    public AttendanceStatus createAttendanceStatus(@RequestBody AttendanceStatus attendanceStatus) {
        return attendanceStatusService.createAttendanceStatus(attendanceStatus);
    }

    @GetMapping("{idAttendanceStatus}") //localhost:5173/attendanceStatus/1
    public AttendanceStatus getAttendanceStatusById(@PathVariable("idAttendanceStatus") Long id) {
        return attendanceStatusService.getAttendanceStatusById(id);
    }

    @PutMapping //localhost:5173/attendanceStatus
    public AttendanceStatus updateAttendanceStatus(@RequestBody AttendanceStatus attendanceStatus) {
        return attendanceStatusService.updateAttendanceStatus(attendanceStatus);
    }

    @GetMapping("attendance/{idAttendance}") //localhost:5173/attendanceStatus/attendance/1
    public AttendanceStatus getAttendanceStatusByAttendanceId(@PathVariable("idAttendance") Long attendanceid) {
        return attendanceStatusService.getAttendanceStatusByAttendanceId(attendanceid);
    }

    @GetMapping("byAttendanceAndStudent") //localhost:5173/attendanceStatus/byAttendanceAndStudent?attendanceid=1&studentid=1
    public AttendanceStatus getAttendanceStatusByAttendanceIdAndStudentId(@RequestParam("attendanceid") Long attendanceid, @RequestParam("studentid") Long studentid) {
        return attendanceStatusService.getAttendanceStatusByAttendanceIdAndStudentId(attendanceid, studentid);
    }


}