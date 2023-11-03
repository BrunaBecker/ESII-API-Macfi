package com.macfi.model;

import com.macfi.model.person.Student;
import com.macfi.model.utils.enums_class.StudentAtAttendanceState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AttendanceStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private StudentAtAttendanceState studentState;

    private boolean studentHasResponded;

    private boolean validated;

    @OneToOne
    private Student student;

    @ManyToOne
    private Attendance attendance;

    @OneToMany(mappedBy = "attendanceStatus")
    private List<Ping> successfulPings;

    @OneToMany(mappedBy = "attendanceStatus")
    private List<Ping> unsuccessfulPings;

    @OneToOne
    private Waiver waiver;


    public boolean addSuccessfulPing(Ping ping) {
        return successfulPings.add(ping);
    }

    public boolean addUnsuccessfulPing(Ping ping) {
        return unsuccessfulPings.add(ping);
    }



}