package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.AttendanceStatus;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.AttendanceStatusDto;
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

    public AttendanceStatusDto createAttendanceStatus(AttendanceStatusDto attendanceStatusDto) {
        AttendanceStatus attendanceStatus = modelMapping.getInstance().mapToEntity(attendanceStatusDto, AttendanceStatus.class);
        return modelMapping.getInstance().mapToDto(attendanceStatusRepository.save(attendanceStatus), AttendanceStatusDto.class);

    }

    public AttendanceStatusDto getAttendanceStatusById(Long id) {
        return attendanceStatusRepository.findById(id)
                .map(attendanceStatus -> modelMapping.getInstance().mapToDto(attendanceStatus, AttendanceStatusDto.class))
                .orElseThrow(() -> new EntityNotFoundException("AttendanceStatus não encontrada"));
    }

    public AttendanceStatusDto updateAttendanceStatus(AttendanceStatusDto attendanceStatusDto) {
        AttendanceStatus attendanceStatus = modelMapping.getInstance().mapToEntity(attendanceStatusDto, AttendanceStatus.class);

        if (!(attendanceStatus.getAttendance().getId().equals(attendanceStatusDto.getAttendance().getId()))) {
            attendanceRepository.findById(attendanceStatus.getAttendance().getId())
                    .orElseThrow(() -> new RuntimeException("AttendanceStatus não encontrada"));
        }
        return modelMapping.getInstance().mapToDto(attendanceStatusRepository.save(attendanceStatus), AttendanceStatusDto.class);
    }

    public List<AttendanceStatusDto> getAttendanceStatusByAttendanceId(Long attendanceid) {
        List<AttendanceStatus> attendanceStatuses = attendanceStatusRepository.FindByAttendanceId(attendanceid);
        return attendanceStatuses.stream().map(attendanceStatus -> modelMapping.getInstance().mapToDto(attendanceStatus, AttendanceStatusDto.class)).collect(java.util.stream.Collectors.toList());
    }

    public AttendanceStatusDto getAttendanceStatusByAttendanceIdAndStudentId(Long attendanceid, Long studentid) {
        return modelMapping.getInstance().mapToDto(attendanceStatusRepository.FindByAttendanceIdAndStudentId(attendanceid, studentid), AttendanceStatusDto.class);
    }


    public List<AttendanceStatusDto> getAttendanceStatus() {
        List<AttendanceStatus> attendanceStatuses = attendanceStatusRepository.findAll();
        return attendanceStatuses.stream().map(attendanceStatus -> modelMapping.getInstance().mapToDto(attendanceStatus, AttendanceStatusDto.class)).collect(java.util.stream.Collectors.toList());
    }

}
