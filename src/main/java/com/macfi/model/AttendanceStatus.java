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
@Table(name = "attendance_status")
@ToString
public class AttendanceStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private StudentAtAttendanceState studentState;

    private boolean studentHasResponded;

    private boolean validated;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "attendance_id", referencedColumnName = "id")
    private Attendance attendance;

    @OneToMany(mappedBy = "attendanceStatus")
    @ToString.Exclude
    private List<Ping> successfulPings;

    @OneToMany(mappedBy = "attendanceStatus")
    @ToString.Exclude
    private List<Ping> unsuccessfulPings;

    @OneToOne(mappedBy = "attendanceStatus")
    private Waiver waiver;

    public AttendanceStatus(StudentAtAttendanceState studentState, boolean studentHasResponded, boolean validated, Student student, Attendance attendance, List<Ping> successfulPings, List<Ping> unsuccessfulPings, Waiver waiver) {
        this.studentState = studentState;
        this.studentHasResponded = studentHasResponded;
        this.validated = validated;
        this.student = student;
        this.attendance = attendance;
        this.successfulPings = successfulPings;
        this.unsuccessfulPings = unsuccessfulPings;
        this.waiver = waiver;
    }

    public AttendanceStatus(StudentAtAttendanceState studentState, boolean validated, Student student, Attendance attendance, List<Ping> successfulPings, List<Ping> unsuccessfulPings, Waiver waiver) {
        this.studentState = studentState;
        this.studentHasResponded = false;
        this.validated = validated;
        this.student = student;
        this.attendance = attendance;
        this.successfulPings = successfulPings;
        this.unsuccessfulPings = unsuccessfulPings;
        this.waiver = waiver;
    }

    public boolean addSuccessfulPing(Ping ping) {
        return successfulPings.add(ping);
    }

    public boolean addUnsuccessfulPing(Ping ping) {
        return unsuccessfulPings.add(ping);
    }


}
