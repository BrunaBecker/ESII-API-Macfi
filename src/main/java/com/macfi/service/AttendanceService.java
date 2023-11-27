package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.InvalidBodyCreateException;
import com.macfi.exception.RuleMacFiException;
import com.macfi.model.Attendance;
import com.macfi.model.utils.Dateformater;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.AttendanceDto;
import com.macfi.repository.AttendanceRepository;
import com.macfi.repository.AttendanceStatusRepository;
import com.macfi.repository.VirtualZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private VirtualZoneRepository virtualZoneRepository;
    @Autowired
    private AttendanceStatusRepository attendanceStatusRepository;

    @Autowired
    private MacfiServices macfiServices;


    public AttendanceDto createAttendance(AttendanceDto attendanceDto) {

        Attendance a = modelMapping.getInstance().mapToEntity(attendanceDto, Attendance.class);
        if (a == null) {
            throw new InvalidBodyCreateException("Invalid body");
        }
        if (a.getClassroom() != null && a.getClassroom().getProfessor() != null) {
            Attendance t = attendanceRepository.findAttendanceHappeningByProfessor(a.getClassroom().getProfessor().getId());
            if (t != null) {
                throw new RuleMacFiException("Professor already has an attendance happening");
            }
        }

        assert a.getClassroom() != null;
        macfiServices.notifyAllStudents(a.getClassroom());

        return modelMapping.getInstance().mapToDto(attendanceRepository.save(a), AttendanceDto.class);
    }

    public void deleteAttendance(Long id) {
        getAttendanceById(id);
        attendanceRepository.deleteById(id);
    }

    public AttendanceDto getAttendanceById(Long id) {
        return modelMapping.getInstance().mapToDto(attendanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendance not found")), AttendanceDto.class);
    }

    public AttendanceDto updateAttendance(AttendanceDto attendanceDto) {
        attendanceRepository.findById(attendanceDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Attendance not found"));
        Attendance attendance1 = modelMapping.getInstance().mapToEntity(attendanceDto, Attendance.class);
        return modelMapping.getInstance().mapToDto(attendanceRepository.save(attendance1), AttendanceDto.class);
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

        Date date;
        try {
            date = Dateformater.parse(dateStr, true, true);
            List<Attendance> attendances = attendanceRepository.findByClassroomIdAndDate(classroomid, date);
            return attendances.stream().map(attendance -> modelMapping.getInstance().mapToDto(attendance, AttendanceDto.class)).collect(Collectors.toList());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public List<AttendanceDto> getAttendancesHappening() {
        List<Attendance> attendances = attendanceRepository.findAllAttendancesHappening();
        return attendances.stream().map(attendance -> modelMapping.getInstance().mapToDto(attendance, AttendanceDto.class)).collect(Collectors.toList());
    }

    public AttendanceDto getAttendancesHappeningByClassroom(Long id) {
        Attendance attendance = attendanceRepository.findAttendanceHappeningByClassroom(id);
        return modelMapping.getInstance().mapToDto(attendance, AttendanceDto.class);
    }

    public AttendanceDto getAttendancesHappeningByStudent(Long id) {
       Attendance attendance = attendanceRepository.findAttendanceHappeningByStudent(id);
        if (attendance == null) {
            throw new EntityNotFoundException("Attendance not found");
        }
        return modelMapping.getInstance().mapToDto(attendance, AttendanceDto.class);
    }


    public AttendanceDto endAttendance(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendance not found"));
        attendance.setHappening(false);
        return modelMapping.getInstance().mapToDto(attendanceRepository.save(attendance), AttendanceDto.class);
    }

    public AttendanceDto startAttendance(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendance not found"));
        attendance.setHappening(true);
        return modelMapping.getInstance().mapToDto(attendanceRepository.save(attendance), AttendanceDto.class);
    }

    public AttendanceDto setAttendanceStatus(Long idAttendance, Long idAttendanceStatus) {
        Attendance attendance = attendanceRepository.findById(idAttendance)
                .orElseThrow(() -> new EntityNotFoundException("Attendance not found"));
        attendance.getAttendancesStatuses().add(attendanceStatusRepository.findById(idAttendanceStatus)
                .orElseThrow(() -> new EntityNotFoundException("AttendanceStatus not found")));

        return modelMapping.getInstance().mapToDto(attendanceRepository.save(attendance), AttendanceDto.class);
    }


    public AttendanceDto setVirtualZone(Long idAttendance, Long idVirtualZone) {
        Attendance attendance = attendanceRepository.findById(idAttendance)
                .orElseThrow(() -> new EntityNotFoundException("Attendance not found"));
        attendance.setVirtualZone(virtualZoneRepository.findById(idVirtualZone)
                .orElseThrow(() -> new EntityNotFoundException("VirtualZone not found")));
        return modelMapping.getInstance().mapToDto(attendanceRepository.save(attendance), AttendanceDto.class);
    }

    public AttendanceDto getAttendancesHappeningByProfessor(Long id) {
        Attendance attendance = attendanceRepository.findAttendanceHappeningByProfessor(id);
        if (attendance == null) {
            throw new EntityNotFoundException("Attendance not found");
        }
        return modelMapping.getInstance().mapToDto(attendance, AttendanceDto.class);
    }

    public Duration getDuration(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendance not found"));
        if (attendance.getStartHour() == null) {
            throw new RuleMacFiException("Attendance not has started hour");
        }
        if (attendance.getEndHour() == null) {
            throw new RuleMacFiException("Attendance not has ended hour, maybe isnt created yet or is happening");
        }
        return attendance.getDuration(attendance.getStartHour(), attendance.getEndHour());
    }
}
