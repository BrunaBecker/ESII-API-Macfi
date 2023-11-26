package com.macfi.model.utils;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class Statistic implements Serializable {

    public String presences;

    public String absences;

    public String justifications;


    public String allAttendances;

    public String frequency;


    public Statistic(String presences, String absences, String justifications, String allAttendances, String frequency) {
        this.presences = presences;
        this.absences = absences;
        this.justifications = justifications;
        this.allAttendances = allAttendances;
        this.frequency = frequency;
    }
}
