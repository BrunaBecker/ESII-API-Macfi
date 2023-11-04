package com.macfi.model;

import com.macfi.model.person.Student;
import com.macfi.model.utils.Comment;
import com.macfi.model.utils.FileMacFI;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "waiver")
public class Waiver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private FileMacFI file;

    private String description;


    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;


    @Temporal(TemporalType.TIMESTAMP)
    private Date acceptionDate;

    private boolean isAccepted;

    @OneToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "Student_id", referencedColumnName = "id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "AttendanceStatus_id", referencedColumnName = "id")
    private AttendanceStatus attendanceStatus;

    public Waiver(Long id, FileMacFI file, String description, Date sendDate, Date acceptionDate, boolean isAccepted, Comment comment, Student student, AttendanceStatus attendanceStatus) {
        this.id = id;
        this.file = file;
        this.description = description;
        this.sendDate = sendDate;
        this.acceptionDate = acceptionDate;
        this.isAccepted = isAccepted;
        this.comment = comment;
        this.student = student;
        this.attendanceStatus = attendanceStatus;
    }
}
