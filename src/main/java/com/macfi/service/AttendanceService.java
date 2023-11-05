package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.Attendance;
import com.macfi.model.utils.Dateformater;
import com.macfi.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance createAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public void deleteAttendance(Long id) {
        getAttendanceById(id);
        attendanceRepository.deleteById(id);
    }

    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendance não encontrada"));
    }

    public Attendance updateAttendance(Attendance attendance) {
        Attendance aAttendance = getAttendanceById(attendance.getId());
        if (!attendance.getId().equals(aAttendance.getId())) {
            attendanceRepository.findById(attendance.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Attendance não encontrada"));
        }
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendances() {
        return attendanceRepository.findAll(Sort.by("id"));
    }


    public List<Attendance> getAttendancesByClassroom(Long id) {
        return attendanceRepository.findByClassroomId(id);
    }

    public List<Attendance> getAttendancesByClassroomAndDate(Long classroomid, String dateStr) {

        Date date = null;
        try {
            date = Dateformater.format(dateStr);
            return attendanceRepository.findByClassroomIdAndDate(classroomid, date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Attendance> getAttendancesHappening() {
        return attendanceRepository.findAttendanceHappening();
    }

    public List<Attendance> getAttendancesHappeningByClassroom(Long id) {
        return attendanceRepository.findAttendanceHappeningByClassroom(id);
    }

    public List<Attendance> getAttendancesHappeningByStudent(Long id) {
        return attendanceRepository.findAttendanceHappeningByStudent(id);
    }

}
