package com.macfi.service;

import com.macfi.model.Attendance;
import com.macfi.model.AttendanceStatus;
import com.macfi.model.utils.Statistic;
import com.macfi.model.utils.enums_class.StudentAtAttendanceState;
import com.macfi.repository.AttendanceRepository;
import com.macfi.repository.AttendanceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StatisticService {

    @Autowired
    private AttendanceStatusRepository attendanceStatusRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;



    public Integer getNumberOfPresentFromStudent(Long idStudent, Long idClassroom) {
        List<AttendanceStatus> attendanceStatuses = attendanceStatusRepository.findByStudentIdAndClassroomId(idStudent, idClassroom);
        Integer numberOfPresent = 0;
        for (AttendanceStatus attendanceStatus : attendanceStatuses) {
            if (attendanceStatus.isValidated() && attendanceStatus.getStudentState() == StudentAtAttendanceState.present) {
                numberOfPresent++;
            }
        }
        return numberOfPresent;
    }

    public Integer getNumberOfAbsentFromStudent(Long idStudent, Long idClassroom) {
        List<AttendanceStatus> attendanceStatuses = attendanceStatusRepository.findByStudentIdAndClassroomId(idStudent, idClassroom);
        Integer numberOfAbsent = 0;
        for (AttendanceStatus attendanceStatus : attendanceStatuses) {
            if (attendanceStatus.isValidated() && attendanceStatus.getStudentState() == StudentAtAttendanceState.absent) {
                numberOfAbsent++;
            }
        }
        return numberOfAbsent;
    }

    public Integer getNumberOfJustifiedFromStudent(Long idStudent, Long idClassroom) {
        List<AttendanceStatus> attendanceStatuses = attendanceStatusRepository.findByStudentIdAndClassroomId(idStudent, idClassroom);
        Integer numberOfLate = 0;
        for (AttendanceStatus attendanceStatus : attendanceStatuses) {
            if (attendanceStatus.isValidated() && attendanceStatus.getStudentState() == StudentAtAttendanceState.justified) {
                numberOfLate++;
            }
        }
        return numberOfLate;
    }


    public Integer totalNumberOfAttendanceInClassroom(Long idClassroom) {
        List<Attendance> attendanceList = attendanceRepository.findByClassroomId(idClassroom);
        return attendanceList.size();
    }

    public String returnTotalAbsentFromAStudent(Long idStudent, Long idClassroom) {
       return "Absent: " + getNumberOfAbsentFromStudent(idStudent, idClassroom);
    }

    public String returnTotalPresentFromAStudent(Long idStudent, Long idClassroom) {
       return "Present: " + getNumberOfPresentFromStudent(idStudent, idClassroom);
    }

    public String returnTotalJustifiedFromAStudent(Long idStudent, Long idClassroom) {
       return "Justified: " + getNumberOfJustifiedFromStudent(idStudent, idClassroom);
    }

    public String returnTotalAttendanceFromClassroom(Long idClassroom) {
       return "Total: " + totalNumberOfAttendanceInClassroom(idClassroom);
    }

    public String percentFrequency(Long idStudent, Long idClassroom) {
        Integer total = totalNumberOfAttendanceInClassroom(idClassroom);

        Integer present = Math.abs(getNumberOfPresentFromStudent(idStudent, idClassroom));

        Integer justified = Math.abs(getNumberOfJustifiedFromStudent(idStudent, idClassroom));

        int f = (present + justified) * 100 / total;

        return "Frequency: " + f + "%";
    }



    public Statistic getStatistics(Long idStudent, Long idClassroom) {
        return new Statistic(
                returnTotalPresentFromAStudent(idStudent, idClassroom),
                returnTotalAbsentFromAStudent(idStudent, idClassroom),
                returnTotalJustifiedFromAStudent(idStudent, idClassroom),
                returnTotalAttendanceFromClassroom(idClassroom),
                percentFrequency(idStudent, idClassroom)
        );
    }
}
