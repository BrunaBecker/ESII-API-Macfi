package com.macfi.model.person;

import com.macfi.model.Classroom;
import com.macfi.model.Location;
import com.macfi.model.Notification;
import com.macfi.model.Setting;
import com.macfi.model.utils.Comment;
import com.macfi.model.utils.Picture;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Professor extends Person {

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Location> locations;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Classroom> classrooms;

    public Professor(String name, String socialName, Date birthDate, Boolean isActive, String cpf, String email, String password, RegisterCollegeID register, Setting setting, Picture profileImagem, List<Comment> commentList, List<Notification> notificationList, List<Location> locations, List<Classroom> classrooms) {
        super(name, socialName, birthDate, isActive, cpf, email, password, register, setting, profileImagem, commentList, notificationList);
        this.locations = locations;
        this.classrooms = classrooms;
    }

    public void addClassroom(Classroom classroom){
        classrooms.add(classroom);
    }

}
