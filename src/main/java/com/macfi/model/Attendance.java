package com.macfi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;
    private String supportingText;

    @Temporal(TemporalType.TIME)
    private Time startHour;

    @Temporal(TemporalType.TIME)
    private Time endHour;

    @Temporal(TemporalType.TIME)
    private Time Duration;

    private boolean isAutomatic;
    private boolean isHappening;

    @OneToOne
    private VirtualZone virtualZone;


    @ManyToOne
    private Classroom classroom;

    @OneToMany(mappedBy = "attendance")
    @ToString.Exclude
    private List<AttendanceStatus> StatusStudentAttendance;


    public boolean addStatusStudentAttendance(AttendanceStatus attendanceStatus) {
       return StatusStudentAttendance.add(attendanceStatus);
    }

    public boolean removeStatusStudentAttendance(AttendanceStatus attendanceStatus) {
        return StatusStudentAttendance.remove(attendanceStatus);
    }


    public Time calculateDuration() {
        if (startHour != null && endHour != null) {
            Duration = new Time(endHour.getTime() - startHour.getTime());
            return Duration;
        }
        throw new RuntimeException("StartHour or EndHour is null");
    }

    public Attendance(Date date,
                      String supportingText,
                      Time startHour,
                      Time endHour,
                      Time duration,
                      boolean isAutomatic,
                      boolean isHappening,
                      VirtualZone virtualZone,
                      Classroom classroom,
                      List<AttendanceStatus> statusStudentAttendance) {
        this.date = date;
        this.supportingText = supportingText;
        this.startHour = startHour;
        this.endHour = endHour;
        Duration = duration;
        this.isAutomatic = isAutomatic;
        this.isHappening = isHappening;
        this.virtualZone = virtualZone;
        this.classroom = classroom;
        StatusStudentAttendance = statusStudentAttendance;
    }

    public Attendance(Date date,
                      String supportingText,
                      Time startHour,
                      Time endHour,
                      Time duration,
                      boolean isHappening,
                      VirtualZone virtualZone,
                      Classroom classroom,
                      List<AttendanceStatus> statusStudentAttendance) {

        this.date = date;
        this.supportingText = supportingText;
        this.startHour = startHour;
        this.endHour = endHour;
        Duration = duration;
        this.isAutomatic = false;
        this.isHappening = isHappening;
        this.virtualZone = virtualZone;
        this.classroom = classroom;
        StatusStudentAttendance = statusStudentAttendance;
    }


}
