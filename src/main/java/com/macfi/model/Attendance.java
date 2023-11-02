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
    private Date date;
    private String supportingText;

    private Time startHour;
    private Time endHour;

    private Time Duration;
    private boolean isAutomatic;
    private boolean isHappening;

    @OneToOne
    private VirtualZone virtualZone;

    @ManyToOne
    private Classroom classroom;

    @OneToMany(mappedBy = "attendance")
    private List<AttendanceStatus> StatusStudentAttendance;

}
