package com.macfi.service;


import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.RuleMacFiException;
import com.macfi.model.Attendance;
import com.macfi.model.AttendanceStatus;
import com.macfi.model.Ping;
import com.macfi.model.utils.Coordinate;
import com.macfi.model.utils.Dateformater;
import com.macfi.model.utils.enums_class.StatusPing;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.AttendanceStatusDto;
import com.macfi.payload.PingDto;
import com.macfi.repository.AttendanceStatusRepository;
import com.macfi.repository.PingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

@Service
public class PingService {

    @Autowired
    private PingRepository pingRepository;


    @Autowired
    private AttendanceStatusRepository attendanceStatusRepository;

    public PingDto createPing(PingDto pingDto) {
        Date d;
        try {
           d = Dateformater.parse(pingDto.getDate(), false, false);
        } catch (ParseException e) {
            throw new RuleMacFiException("Invalid date");
        }


        AttendanceStatus attendanceStatus;
        AttendanceStatusDto attendanceStatusDto = pingDto.getAttendanceStatus();
        try {
            attendanceStatus = attendanceStatusRepository.findById(attendanceStatusDto.getId()).orElseThrow(()-> new EntityNotFoundException("Attendance status not found"));
        } catch (Exception e) {
            attendanceStatus = modelMapping.getInstance().mapToEntity(attendanceStatusDto, AttendanceStatus.class);
        }

        Attendance attendance = attendanceStatus.getAttendance();
        Ping ping = new Ping();

        ping.setDate(d);
        ping.setIp(pingDto.getIp());
        ping.setCoordinate(modelMapping.getInstance().mapToEntity(pingDto.getCoordinate(), Coordinate.class));
        ping.setIsContinuos(pingDto.getIsContinuos());
        ping.setAttendanceStatus(attendanceStatus);

        if (attendanceStatus.getSuccessfulPings() == null) {
            attendanceStatus.setSuccessfulPings(new ArrayList<>());
        } if (attendanceStatus.getUnsuccessfulPings() == null) {
            attendanceStatus.setUnsuccessfulPings(new ArrayList<>());
        }

        if (attendance.isHappening()) {
            ping.setStatus(StatusPing.validAttendance);

            if (ping.inCorrectLocation(ping.getCoordinate().getLatitude(),
                    ping.getCoordinate().getLongitude(),
                    attendance.getVirtualZone().getLocation().getCoordinate().getLatitude(),
                    attendance.getVirtualZone().getLocation().getCoordinate().getLongitude())) {
                    ping.setStatus(StatusPing.successful);
                try {
                    attendanceStatus.addSuccessfulPing(ping);

                } catch (Exception e) {
                    ping.setStatus(StatusPing.unsuccessful);
                    attendanceStatus.addUnsuccessfulPing(ping);
                }

            } else {
                ping.setStatus(StatusPing.unsuccessful);
                attendanceStatus.addUnsuccessfulPing(ping);
            }

        } else {
            ping.setStatus(StatusPing.invalidAttendance);
        }
        attendanceStatusRepository.save(attendanceStatus);
        pingRepository.save(ping);
        PingDto pingDto1 = modelMapping.getInstance().mapToDto(ping, PingDto.class);
        pingDto1.setDate(Dateformater.format(ping.getDate()));
        return pingDto1;
    }

    public PingDto getPingById(Long id) {
        Ping ping = pingRepository.findById(id).orElseThrow(() -> new RuntimeException("Ping not found"));
        return modelMapping.getInstance().mapToDto(ping, PingDto.class);
    }


}
