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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "file_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private FileMacFI file;

    private String description;


    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;


    @Temporal(TemporalType.TIMESTAMP)
    private Date acceptionDate;

    private boolean isAccepted;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "attendance_status_id", referencedColumnName = "id")
    private AttendanceStatus attendanceStatus;

    public Waiver( FileMacFI file, String description, Date sendDate, Date acceptionDate, boolean isAccepted, Comment comment, Student student, AttendanceStatus attendanceStatus) {
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
