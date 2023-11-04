package com.macfi.service;

import com.macfi.model.AttendanceStatus;
import com.macfi.repository.AttendanceRepository;
import com.macfi.repository.AttendanceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceStatusService {

    @Autowired
    private AttendanceStatusRepository attendanceStatusRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    public AttendanceStatus createAttendanceStatus(AttendanceStatus attendanceStatus) {
        return attendanceStatusRepository.save(attendanceStatus);
    }

    public AttendanceStatus getAttendanceStatusById(Long id) {
        return attendanceStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AttendanceStatus não encontrada"));
    }

    public AttendanceStatus updateAttendanceStatus(AttendanceStatus attendanceStatus) {
        AttendanceStatus aAttendanceStatus = getAttendanceStatusById(attendanceStatus.getId());

        if  (!(attendanceStatus.getAttendance().getId().equals(aAttendanceStatus.getAttendance().getId()))){
            attendanceRepository.findById(attendanceStatus.getAttendance().getId())
                    .orElseThrow(() -> new RuntimeException("AttendanceStatus não encontrada"));
        }
        return attendanceStatusRepository.save(attendanceStatus);
    }

    public List<AttendanceStatus> getAttendanceStatusByAttendanceId(Long attendanceid) {
        return attendanceStatusRepository.FindByAttendanceId(attendanceid);
    }

    public AttendanceStatus getAttendanceStatusByAttendanceIdAndStudentId(Long attendanceid, Long studentid) {
        return attendanceStatusRepository.FindByAttendanceIdAndStudentId(attendanceid, studentid);
    }


    public List<AttendanceStatus> getAttendanceStatus() {
        return attendanceStatusRepository.findAll();
    }

}
