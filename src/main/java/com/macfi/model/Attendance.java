package com.macfi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;
    private String supportingText;

    @Temporal(TemporalType.TIME)
    private LocalTime startHour;

    @Temporal(TemporalType.TIME)
    private LocalTime endHour;

    @Temporal(TemporalType.TIME)
    private Duration duration;

    private boolean isAutomatic;
    private boolean isHappening;

    @OneToOne
    @JoinColumn(name = "virtual_zone_id", referencedColumnName = "id")
    private VirtualZone virtualZone;


    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    private Classroom classroom;

    @OneToMany(mappedBy = "attendance", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<AttendanceStatus> StatusStudentAttendance;


    public Attendance(Date date,
                      String supportingText,
                      LocalTime startHour,
                      LocalTime endHour,
                      Duration duration,
                      boolean isAutomatic,
                      boolean isHappening,
                      VirtualZone virtualZone,
                      Classroom classroom,
                      List<AttendanceStatus> statusStudentAttendance) {
        this.date = date;
        this.supportingText = supportingText;
        this.startHour = startHour;
        this.endHour = endHour;
        this.duration = duration;
        this.isAutomatic = isAutomatic;
        this.isHappening = isHappening;
        this.virtualZone = virtualZone;
        this.classroom = classroom;
        StatusStudentAttendance = statusStudentAttendance;
    }

    public Attendance(Date date,
                      String supportingText,
                      LocalTime startHour,
                      LocalTime endHour,
                      Duration duration,
                      boolean isHappening,
                      VirtualZone virtualZone,
                      Classroom classroom,
                      List<AttendanceStatus> statusStudentAttendance) {

        this.date = date;
        this.supportingText = supportingText;
        this.startHour = startHour;
        this.endHour = endHour;
        this.duration = duration;
        this.isAutomatic = false;
        this.isHappening = isHappening;
        this.virtualZone = virtualZone;
        this.classroom = classroom;
        StatusStudentAttendance = statusStudentAttendance;
    }

    public boolean addStatusStudentAttendance(AttendanceStatus attendanceStatus) {
        return StatusStudentAttendance.add(attendanceStatus);
    }

    public boolean removeStatusStudentAttendance(AttendanceStatus attendanceStatus) {
        return StatusStudentAttendance.remove(attendanceStatus);
    }

    public Duration calculateDuration(LocalTime startHour, LocalTime endHour) {
        return Duration.between(startHour, endHour);
    }


}
