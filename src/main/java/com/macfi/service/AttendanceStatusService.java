package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.RuleMacFiException;
import com.macfi.model.AttendanceStatus;
import com.macfi.model.Ping;
import com.macfi.model.Waiver;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.AttendanceStatusDto;
import com.macfi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceStatusService {

    @Autowired
    private AttendanceStatusRepository attendanceStatusRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;


    @Autowired
    private WaiverRepository waiverRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PingRepository pingRepository;

    public AttendanceStatusDto createAttendanceStatus(AttendanceStatusDto attendanceStatusDto) {

        if (attendanceStatusRepository.FindByAttendanceIdAndStudentId(attendanceStatusDto.getAttendance().getId(), attendanceStatusDto.getStudent().getId()) != null) {
            throw new RuleMacFiException("AttendanceStatus already exists");
        }

        AttendanceStatus attendanceStatus = modelMapping.getInstance().mapToEntity(attendanceStatusDto, AttendanceStatus.class);
        return modelMapping.getInstance().mapToDto(attendanceStatusRepository.save(attendanceStatus), AttendanceStatusDto.class);

    }

    public AttendanceStatusDto getAttendanceStatusById(Long id) {
        return modelMapping.getInstance().mapToDto(attendanceStatusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AttendanceStatus not found")), AttendanceStatusDto.class);
    }

    public AttendanceStatusDto updateAttendanceStatus(AttendanceStatusDto attendanceStatusDto) {
        attendanceStatusRepository.findById(attendanceStatusDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("AttendanceStatus not found"));
        AttendanceStatus attendanceStatus1 = modelMapping.getInstance().mapToEntity(attendanceStatusDto, AttendanceStatus.class);
        return modelMapping.getInstance().mapToDto(attendanceStatusRepository.save(attendanceStatus1), AttendanceStatusDto.class);
    }

    public List<AttendanceStatusDto> getAttendanceStatusByAttendanceId(Long attendanceid) {
        List<AttendanceStatus> attendanceStatuses = attendanceStatusRepository.FindByAttendanceId(attendanceid);
        return attendanceStatuses.stream().map(attendanceStatus -> modelMapping.getInstance().mapToDto(attendanceStatus, AttendanceStatusDto.class)).collect(java.util.stream.Collectors.toList());
    }

    public AttendanceStatusDto getAttendanceStatusByAttendanceIdAndStudentId(Long attendanceid, Long studentid) {
        AttendanceStatus ats = attendanceStatusRepository.FindByAttendanceIdAndStudentId(attendanceid, studentid);

        if (ats == null) {
            throw new EntityNotFoundException("AttendanceStatus not found");
        }

        return modelMapping.getInstance().mapToDto(attendanceStatusRepository.FindByAttendanceIdAndStudentId(attendanceid, studentid), AttendanceStatusDto.class);
    }


    public List<AttendanceStatusDto> getAttendanceStatus() {
        List<AttendanceStatus> attendanceStatuses = attendanceStatusRepository.findAll();
        return attendanceStatuses.stream().map(attendanceStatus -> modelMapping.getInstance().mapToDto(attendanceStatus, AttendanceStatusDto.class)).collect(java.util.stream.Collectors.toList());
    }

    public List<AttendanceStatusDto> getAttendanceStatusByStudentId(Long studentid) {
        List<AttendanceStatus> attendanceStatuses = attendanceStatusRepository.FindByStudentId(studentid);
        return attendanceStatuses.stream().map(attendanceStatus -> modelMapping.getInstance().mapToDto(attendanceStatus, AttendanceStatusDto.class)).collect(java.util.stream.Collectors.toList());
    }


    public AttendanceStatusDto respondAttendanceStatus(Long id) {
        AttendanceStatus attendanceStatus = attendanceStatusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AttendanceStatus not found"));
        attendanceStatus.setStudentHasResponded(true);
        return modelMapping.getInstance().mapToDto(attendanceStatusRepository.save(attendanceStatus), AttendanceStatusDto.class);

    }

    public AttendanceStatusDto invalidateAttendanceStatus(Long id) {
        AttendanceStatus attendanceStatus = attendanceStatusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AttendanceStatus not found"));
        attendanceStatus.setValidated(false);
        return modelMapping.getInstance().mapToDto(attendanceStatusRepository.save(attendanceStatus), AttendanceStatusDto.class);
    }

    public AttendanceStatusDto validateAttendanceStatus(Long id) {
        AttendanceStatus attendanceStatus = attendanceStatusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AttendanceStatus not found"));
        attendanceStatus.setValidated(true);
        return modelMapping.getInstance().mapToDto(attendanceStatusRepository.save(attendanceStatus), AttendanceStatusDto.class);
    }

    public AttendanceStatusDto setWaiver(Long idAttendanceStatus, Long idWaiver) {
        Waiver w = waiverRepository.findById(idWaiver)
                .orElseThrow(() -> new EntityNotFoundException("Waiver not found"));
        AttendanceStatus attendanceStatus = attendanceStatusRepository.findById(idAttendanceStatus)
                .orElseThrow(() -> new EntityNotFoundException("AttendanceStatus not found"));
        attendanceStatus.setWaiver(w);
        return modelMapping.getInstance().mapToDto(attendanceStatusRepository.save(attendanceStatus), AttendanceStatusDto.class);
    }

    public AttendanceStatusDto setUnsuccessfulPing(Long idAttendanceStatus, Long idPing) {
        Ping p = pingRepository.findById(idPing)
                .orElseThrow(() -> new EntityNotFoundException("Ping not found"));

        AttendanceStatus attendanceStatus = attendanceStatusRepository.findById(idAttendanceStatus)
                .orElseThrow(() -> new EntityNotFoundException("AttendanceStatus not found"));
        attendanceStatus.getUnsuccessfulPings().add(p);
        return modelMapping.getInstance().mapToDto(attendanceStatusRepository.save(attendanceStatus), AttendanceStatusDto.class);

    }

    public AttendanceStatusDto setSuccessfulPing(Long idAttendanceStatus, Long idPing) {
        Ping p = pingRepository.findById(idPing)
                .orElseThrow(() -> new EntityNotFoundException("Ping not found"));
        AttendanceStatus attendanceStatus = attendanceStatusRepository.findById(idAttendanceStatus)
                .orElseThrow(() -> new EntityNotFoundException("AttendanceStatus not found"));
        attendanceStatus.getSuccessfulPings().add(p);
        return modelMapping.getInstance().mapToDto(attendanceStatusRepository.save(attendanceStatus), AttendanceStatusDto.class);
    }


    public AttendanceStatusDto removeUnsuccessfulPing(Long idAttendanceStatus, Long idPing) {
        Ping p = pingRepository.findById(idPing)
                .orElseThrow(() -> new EntityNotFoundException("Ping not found"));
        AttendanceStatus attendanceStatus = attendanceStatusRepository.findById(idAttendanceStatus)
                .orElseThrow(() -> new EntityNotFoundException("AttendanceStatus not found"));
        attendanceStatus.getUnsuccessfulPings().remove(p);
        return modelMapping.getInstance().mapToDto(attendanceStatusRepository.save(attendanceStatus), AttendanceStatusDto.class);
    }

    public AttendanceStatusDto removeSuccessfulPing(Long idAttendanceStatus, Long idPing) {
        Ping p = pingRepository.findById(idPing)
                .orElseThrow(() -> new EntityNotFoundException("Ping not found"));
        AttendanceStatus attendanceStatus = attendanceStatusRepository.findById(idAttendanceStatus)
                .orElseThrow(() -> new EntityNotFoundException("AttendanceStatus not found"));
        attendanceStatus.getSuccessfulPings().remove(p);
        return modelMapping.getInstance().mapToDto(attendanceStatusRepository.save(attendanceStatus), AttendanceStatusDto.class);
    }


    public List<AttendanceStatusDto> getAttendanceStatusByStudentIdAndClassroomId(Long studentid, Long classroomid) {
        List<AttendanceStatus> attendanceStatuses = attendanceStatusRepository.findByStudentIdAndClassroomId(studentid, classroomid);
        return attendanceStatuses.stream().map(attendanceStatus -> modelMapping.getInstance().mapToDto(attendanceStatus, AttendanceStatusDto.class)).collect(java.util.stream.Collectors.toList());
    }
}
