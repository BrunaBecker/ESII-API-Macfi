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

    private boolean isAutomatic;
    private boolean isHappening;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "virtual_zone_id", referencedColumnName = "id")
    private VirtualZone virtualZone;


    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    private Classroom classroom;

    @OneToMany(mappedBy = "attendance", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<AttendanceStatus> attendancesStatuses;


    public Attendance(Date date,
                      String supportingText,
                      LocalTime startHour,
                      LocalTime endHour,
                      boolean isAutomatic,
                      boolean isHappening,
                      VirtualZone virtualZone,
                      Classroom classroom,
                      List<AttendanceStatus> attendancesStatuses) {
        this.date = date;
        this.supportingText = supportingText;
        this.startHour = startHour;
        this.endHour = endHour;
        this.isAutomatic = isAutomatic;
        this.isHappening = isHappening;
        this.virtualZone = virtualZone;
        this.classroom = classroom;
        this.attendancesStatuses = attendancesStatuses;
    }

    public Attendance(Date date,
                      String supportingText,
                      LocalTime startHour,
                      LocalTime endHour,
                      boolean isHappening,
                      VirtualZone virtualZone,
                      Classroom classroom,
                      List<AttendanceStatus> attendancesStatuses) {

        this.date = date;
        this.supportingText = supportingText;
        this.startHour = startHour;
        this.endHour = endHour;
        this.isAutomatic = false;
        this.isHappening = isHappening;
        this.virtualZone = virtualZone;
        this.classroom = classroom;
        this.attendancesStatuses = attendancesStatuses;
    }

    public boolean addStatusStudentAttendance(AttendanceStatus attendanceStatus) {
        return attendancesStatuses.add(attendanceStatus);
    }

    public boolean removeStatusStudentAttendance(AttendanceStatus attendanceStatus) {
        return attendancesStatuses.remove(attendanceStatus);
    }

    public Duration getDuration(LocalTime startHour, LocalTime endHour) {
        return Duration.between(startHour, endHour);
    }


}
