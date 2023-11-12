package com.macfi.model.person;

import com.macfi.model.*;
import com.macfi.model.utils.Comment;
import com.macfi.model.utils.Picture;
import com.macfi.model.utils.RegisterCollegeID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@PrimaryKeyJoinColumn(name = "id")
public class Student extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_class", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "class_id", referencedColumnName = "id"))
    @ToString.Exclude
    private List<Classroom> classrooms;

    @OneToMany
    @JoinColumn(name = "waiver_id", referencedColumnName = "id")
    @ToString.Exclude
    private List<Waiver> waivers;

    @OneToMany
    @JoinColumn(name = "attendance_id", referencedColumnName = "id")
    @ToString.Exclude
    private List<AttendanceStatus> attendanceStatuses;


    public Student(String name, String socialName, Date birthDate, Boolean isActive, String cpf, String email, String password, RegisterCollegeID register, Setting setting, Picture profileImagem, List<Comment> commentList, List<Notification> notificationList, List<Classroom> classrooms, List<Waiver> waivers, List<AttendanceStatus> attendances) {
        super(name, socialName, birthDate, isActive, cpf, email, password, register, setting, profileImagem, commentList, notificationList);
        this.classrooms = classrooms;
        this.waivers = waivers;
        this.attendanceStatuses = attendances;
    }


}
