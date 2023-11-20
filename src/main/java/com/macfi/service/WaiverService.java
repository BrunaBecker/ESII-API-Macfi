package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.AttendanceStatus;
import com.macfi.model.Waiver;
import com.macfi.model.utils.Comment;
import com.macfi.model.utils.Dateformater;
import com.macfi.model.utils.FileMacFI;
import com.macfi.model.utils.FileMacFIRepository;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.AttendanceStatusDto;
import com.macfi.payload.WaiverDto;
import com.macfi.repository.AttendanceStatusRepository;
import com.macfi.repository.CommentRepository;
import com.macfi.repository.WaiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
public class WaiverService {
    @Autowired
    private  AttendanceStatusRepository attendanceStatusRepository;
    @Autowired
    private  CommentRepository commentRepository;
    @Autowired
    private  FileMacFIRepository fileMacFIRepository;
    @Autowired
    public WaiverRepository waiverRepository;


    public WaiverDto createWaiver(WaiverDto waiverDto) {
        return modelMapping.getInstance().mapToDto(waiverRepository.save(modelMapping.getInstance().mapToEntity(waiverDto, Waiver.class)), WaiverDto.class);
    }

    public WaiverDto getWaiverByStudentAndClassroom(Long id, Long idClassroom) {
        return modelMapping.getInstance().mapToDto(waiverRepository.findByStudentIdAndClassroomId(id, idClassroom), WaiverDto.class);
    }

    public WaiverDto getWaiverById(Long id) {
        return modelMapping.getInstance().mapToDto(waiverRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("waiver not found")), WaiverDto.class);
    }


    public WaiverDto addAttendanceStatus(Long idWaiver, AttendanceStatusDto attendanceStatusDto) {
        AttendanceStatus attendanceStatus;
        try {
            attendanceStatus = attendanceStatusRepository.findById(attendanceStatusDto.getId()).orElseThrow(() -> new EntityNotFoundException("AttendanceStatus not found"));
        } catch (EntityNotFoundException e) {
            attendanceStatus = modelMapping.getInstance().mapToEntity(attendanceStatusDto, AttendanceStatus.class);
        }
        Waiver waiver = waiverRepository.findById(idWaiver).orElseThrow(() -> new EntityNotFoundException("Waiver not found"));
        waiver.setAttendanceStatus(attendanceStatus);
        return modelMapping.getInstance().mapToDto(waiverRepository.save(waiver), WaiverDto.class);
    }

    public WaiverDto setAttendanceStatus(Long idWaiver, Long idAttendanceStatus) {
        Waiver waiver = waiverRepository.findById(idWaiver).orElseThrow(() -> new EntityNotFoundException("Waiver not found"));
        AttendanceStatus attendanceStatus = attendanceStatusRepository.findById(idAttendanceStatus).orElseThrow(() -> new EntityNotFoundException("AttendanceStatus not found"));
        waiver.setAttendanceStatus(attendanceStatus);
        return modelMapping.getInstance().mapToDto(waiverRepository.save(waiver), WaiverDto.class);
    }

    public WaiverDto setAccepted(Long idWaiver) {
        Waiver waiver = waiverRepository.findById(idWaiver).orElseThrow(() -> new EntityNotFoundException("Waiver not found"));
        waiver.setAccepted(true);
        return modelMapping.getInstance().mapToDto(waiverRepository.save(waiver), WaiverDto.class);
    }

    public WaiverDto setRejected(Long idWaiver) {
        Waiver waiver = waiverRepository.findById(idWaiver).orElseThrow(() -> new EntityNotFoundException("Waiver not found"));
        waiver.setAccepted(false);
        return modelMapping.getInstance().mapToDto(waiverRepository.save(waiver), WaiverDto.class);
    }

    public WaiverDto setComment(Long idWaiver, Long commentId) {
        Waiver waiver = waiverRepository.findById(idWaiver).orElseThrow(() -> new EntityNotFoundException("Waiver not found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        waiver.setComment(comment);
        return modelMapping.getInstance().mapToDto(waiverRepository.save(waiver), WaiverDto.class);
    }

    public WaiverDto setFile(Long idWaiver, Long fileId) {
        Waiver waiver = waiverRepository.findById(idWaiver).orElseThrow(() -> new EntityNotFoundException("Waiver not found"));
        FileMacFI file = fileMacFIRepository.findById(fileId).orElseThrow(() -> new EntityNotFoundException("File not found"));
        waiver.setFile(file);
        return modelMapping.getInstance().mapToDto(waiverRepository.save(waiver), WaiverDto.class);
    }

    public WaiverDto setAcceptionDate(Long idWaiver, String acceptionDate) throws ParseException {
        Date date = Dateformater.format(acceptionDate);
        Waiver waiver = waiverRepository.findById(idWaiver).orElseThrow(() -> new EntityNotFoundException("Waiver not found"));
        waiver.setAcceptionDate(date);
        return modelMapping.getInstance().mapToDto(waiverRepository.save(waiver), WaiverDto.class);
    }
}
