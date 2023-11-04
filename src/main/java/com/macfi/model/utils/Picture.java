package com.macfi.model.utils;

import jakarta.persistence.Entity;
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
public class Picture extends FileMacFI {

    public Picture(Long id, String linkFile, String nameFile, String typeFile, Integer sizeFile, Date dateFile) {
        super(id, linkFile, nameFile, typeFile, sizeFile, dateFile);
    }
}
