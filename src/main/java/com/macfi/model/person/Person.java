package com.macfi.model.person;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.macfi.model.Notification;
import com.macfi.model.Setting;
import com.macfi.model.utils.Comment;
import com.macfi.model.utils.Picture;
import com.macfi.model.utils.RegisterCollegeID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;
    protected String name;
    protected String socialName;

    @Temporal(TemporalType.DATE)
    protected Date birthDate;

    protected Boolean isActive;
    protected String cpf;
    protected String email;
    protected String password;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "register_id")

    protected RegisterCollegeID register;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "setting_id")

    protected Setting setting;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_image_id")

    protected Picture profileImage;

    @OneToMany(mappedBy = "author")

    protected List<Comment> comments;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    protected List<Notification> notifications;

    //todo [RF-013]


    public Person(String name, String socialName, Date birthDate, Boolean isActive, String cpf, String email, String password, RegisterCollegeID register, Setting setting, Picture profileImage, List<Comment> comments, List<Notification> notifications) {
        this.name = name;
        this.socialName = socialName;
        this.birthDate = birthDate;
        this.isActive = isActive;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.register = register;
        this.setting = setting;
        this.profileImage = profileImage;
        this.comments = comments;
        this.notifications = notifications;
    }
}
