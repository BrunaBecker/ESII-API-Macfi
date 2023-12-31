package com.macfi.model.person;

import com.macfi.model.Notification;
import com.macfi.model.Setting;
import com.macfi.model.utils.Comment;
import com.macfi.model.utils.Picture;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
//TENTEI COLOCAR COMO ABSTRATO SEGUIDO AS REGRAS DE POO POREM SO DEU PROBLEMA
// PROBLEMA DE TABELA BELEZA FOI CONSERTEI AI DEPOIS DEU PROBLEMA NAO SEI QUE LA NO REPOSITORY
// FUI E ACERTEI AI DEU PROBLEMA NO CONTROLLER FUI CONSERTEI E DESISTO
// PORQUE AGORA DA PROBLEMA NO MAPPING E EU NAO AGUENTO MAIS. FÉ STI
public class Person {
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
    @JoinColumn(name = "setting_id")
    protected Setting setting;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_image_id")
    protected Picture profileImage;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    protected List<Comment> comments;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected List<Notification> notifications;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "register_id")
    protected RegisterCollegeID register;

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
