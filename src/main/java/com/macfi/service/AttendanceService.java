package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.Attendance;
import com.macfi.model.utils.Dateformater;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.AttendanceDto;
import com.macfi.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public AttendanceDto createAttendance(AttendanceDto attendanceDto) {
        Attendance a = modelMapping.getInstance().mapToEntity(attendanceDto, Attendance.class);
        return modelMapping.getInstance().mapToDto(attendanceRepository.save(a), AttendanceDto.class);
    }

    public void deleteAttendance(Long id) {
        getAttendanceById(id);
        attendanceRepository.deleteById(id);
    }

    public AttendanceDto getAttendanceById(Long id) {
        return modelMapping.getInstance().mapToDto(attendanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendance não encontrada")), AttendanceDto.class);
    }

    public AttendanceDto updateAttendance(AttendanceDto attendanceDto) {
        Attendance attendance = modelMapping.getInstance().mapToEntity(attendanceDto, Attendance.class);
        if (!attendanceDto.getId().equals(attendance.getId())) {
            attendanceRepository.findById(attendance.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Attendance não encontrada"));
        }
        return modelMapping.getInstance().mapToDto(attendanceRepository.save(attendance), AttendanceDto.class);
    }

    public List<AttendanceDto> getAttendances() {
        List<Attendance> attendances = attendanceRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return attendances.stream().map(attendance -> modelMapping.getInstance().mapToDto(attendance, AttendanceDto.class)).collect(Collectors.toList());
    }


    public List<AttendanceDto> getAttendancesByClassroom(Long id) {
        List<Attendance> attendances = attendanceRepository.findByClassroomId(id);
        return attendances.stream().map(attendance -> modelMapping.getInstance().mapToDto(attendance, AttendanceDto.class)).collect(Collectors.toList());
    }

    public List<AttendanceDto> getAttendancesByClassroomAndDate(Long classroomid, String dateStr) {

        Date date = null;
        try {
            date = Dateformater.format(dateStr);
            List<Attendance> attendances = attendanceRepository.findByClassroomIdAndDate(classroomid, date);
           return attendances.stream().map(attendance -> modelMapping.getInstance().mapToDto(attendance, AttendanceDto.class)).collect(Collectors.toList());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public List<AttendanceDto> getAttendancesHappening() {
        List<Attendance> attendances = attendanceRepository.findAttendanceHappening();
        return attendances.stream().map(attendance -> modelMapping.getInstance().mapToDto(attendance, AttendanceDto.class)).collect(Collectors.toList());
    }

    public List<AttendanceDto> getAttendancesHappeningByClassroom(Long id) {
        List<Attendance> attendances = attendanceRepository.findAttendanceHappeningByClassroom(id);
        return attendances.stream().map(attendance -> modelMapping.getInstance().mapToDto(attendance, AttendanceDto.class)).collect(Collectors.toList());
    }

    public List<AttendanceDto> getAttendancesHappeningByStudent(Long id) {
        List<Attendance> attendances = attendanceRepository.findAttendanceHappeningByStudent(id);
        return attendances.stream().map(attendance -> modelMapping.getInstance().mapToDto(attendance, AttendanceDto.class)).collect(Collectors.toList());
    }

    public AttendanceDto getAttendanceHappeningById(Long id) {
        return modelMapping.getInstance().mapToDto(attendanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendance não encontrada")), AttendanceDto.class);
    }
}
