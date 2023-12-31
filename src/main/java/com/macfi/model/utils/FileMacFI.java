package com.macfi.model.utils;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "file_macfi")
@Inheritance(strategy = InheritanceType.JOINED)
public class FileMacFI {

    public String linkFile;
    public String nameFile;
    public String typeFile;
    public Integer sizeFile; // in bytes
    @Temporal(TemporalType.TIMESTAMP)
    public Date dateFile;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //todo file atributte


    public FileMacFI(String linkFile, String nameFile, String typeFile, Integer sizeFile, Date dateFile) {
        this.linkFile = linkFile;
        this.nameFile = nameFile;
        this.typeFile = typeFile;
        this.sizeFile = sizeFile;
        this.dateFile = dateFile;
    }
}
