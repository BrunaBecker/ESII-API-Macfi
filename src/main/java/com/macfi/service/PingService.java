package com.macfi.service;


import com.macfi.model.Attendance;
import com.macfi.model.AttendanceStatus;
import com.macfi.model.Ping;
import com.macfi.model.utils.enums_class.StatusPing;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.PingDto;
import com.macfi.repository.AttendanceStatusRepository;
import com.macfi.repository.PingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PingService {

    @Autowired
    private PingRepository pingRepository;


    @Autowired
    private AttendanceStatusRepository attendanceStatusRepository;

    public PingDto createPing(PingDto pingDto) {
        AttendanceStatus attendanceStatus = attendanceStatusRepository.findById(pingDto.getAttendanceStatusId()).orElseThrow(() -> new RuntimeException("AttendanceStatus not found"));
        Attendance attendance = attendanceStatus.getAttendance();

        Ping ping = modelMapping.getInstance().mapToEntity(pingDto, Ping.class);

        if (attendance.isHappening()) {
            ping.setStatus(StatusPing.validAttendance);


            if (ping.inCorrectLocation(ping.getCoordinate().getLatitude(),
                    ping.getCoordinate().getLongitude(),
                    attendance.getVirtualZone().getLocation().getCoordinate().getLatitude(),
                    attendance.getVirtualZone().getLocation().getCoordinate().getLongitude())) {
                ping.setStatus(StatusPing.successful);
                if (!attendanceStatus.addSuccessfulPing(ping)) {
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
        pingRepository.save(ping);
        return modelMapping.getInstance().mapToDto(ping, PingDto.class);
    }

    public PingDto getPingById(Long id) {
        Ping ping = pingRepository.findById(id).orElseThrow(() -> new RuntimeException("Ping not found"));
        return modelMapping.getInstance().mapToDto(ping, PingDto.class);
    }


}
