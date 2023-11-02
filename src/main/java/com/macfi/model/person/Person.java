package com.macfi.model.person;

import com.macfi.model.Setting;
import com.macfi.model.utils.Comment;
import com.macfi.model.utils.Picture;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@MappedSuperclass
@Entity
@Getter
@Setter
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    protected String socialName;
    protected Date birthDate;
    protected Boolean isActive;
    protected String cpf;
    protected String email;
    protected String password;

    @OneToOne
    protected Setting setting;

    //todo procurar como a gente salva imagens no banco
    protected Picture imagem;

    @OneToMany(mappedBy = "author")
    protected List<Comment> commentList;


}
