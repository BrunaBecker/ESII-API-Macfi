package com.macfi.model;

import com.macfi.model.person.Student;
import com.macfi.model.utils.Comment;
import com.macfi.model.utils.FileMacFI;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "waiver")
public class Waiver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private FileMacFI file;

    private String description;

    private Date sendDate;

    private Date acceptionDate;

    private boolean isAccepted;

    @OneToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "Student_id", referencedColumnName = "id")
    private Student student;


}
