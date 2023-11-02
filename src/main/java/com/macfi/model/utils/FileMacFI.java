package com.macfi.model.utils;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Getter
@Setter
public class FileMacFI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public String linkFile;

    public String nameFile;

    public String typeFile;

    public Integer sizeFile; // in bytes

    public Date dateFile;


}
