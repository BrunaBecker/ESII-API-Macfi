package com.macfi.service;


import com.macfi.model.Attendance;
import com.macfi.model.AttendanceStatus;
import com.macfi.model.Ping;
import com.macfi.model.utils.enums_class.StatusPing;
import com.macfi.repository.PingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PingService {

    @Autowired
    private PingRepository pingRepository;


    @Autowired
    private AttendanceStatusService attendanceStatusService;

    public Ping createPing(Ping ping) {
        AttendanceStatus attendanceStatus = attendanceStatusService.getAttendanceStatusById(ping.getAttendanceStatus().getId());
        Attendance attendance = attendanceStatus.getAttendance();
        if (attendance.isHappening())  {
            ping.setStatus(StatusPing.valid_attedance);

            if (ping.inCorrectLocation(ping.getCoordinates().getLatitude(),
                    ping.getCoordinates().getLongitude(),
                    attendance.getVirtualZone().getLocation().getCoordinates().getLatitude(),
                    attendance.getVirtualZone().getLocation().getCoordinates().getLongitude())) {

                if (attendanceStatus.addSuccessfulPing(ping)) {
                    ping.setStatus(StatusPing.successful);
                } else {
                    ping.setStatus(StatusPing.unsuccessful);
                }
            } else {
                ping.setStatus(StatusPing.unsuccessful);
            }

        } else {
            ping.setStatus(StatusPing.invalid_attendance);
        }
        pingRepository.save(ping);
        return ping;
    }



}
