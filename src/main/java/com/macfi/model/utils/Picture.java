package com.macfi.model.utils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
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
@PrimaryKeyJoinColumn(name = "id")
public class Picture extends FileMacFI {

    public Picture( String linkFile, String nameFile, String typeFile, Integer sizeFile, Date dateFile) {
        super( linkFile, nameFile, typeFile, sizeFile, dateFile);
    }
}
