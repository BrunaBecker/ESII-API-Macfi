package com.macfi.model.person;

import com.macfi.model.Classroom;
import com.macfi.model.Location;
import com.macfi.model.Notification;
import com.macfi.model.Setting;
import com.macfi.model.utils.Comment;
import com.macfi.model.utils.Picture;
import com.macfi.model.utils.RegisterCollegeID;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Professor extends Person {

    @OneToMany(mappedBy = "professor", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Location> locations;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Classroom> classrooms;

    public Professor(Long id, String name, String socialName, Date birthDate, Boolean isActive, String cpf, String email, String password, RegisterCollegeID register, Setting setting, Picture profileImagem, List<Comment> commentList, List<Notification> notificationList, List<Location> locations, List<Classroom> classrooms) {
        super(id, name, socialName, birthDate, isActive, cpf, email, password, register, setting, profileImagem, commentList, notificationList);
        this.locations = locations;
        this.classrooms = classrooms;
    }
}
