package com.macfi.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.macfi.model.person.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "setting")
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean automaticSaveLocalization;
    private boolean usePredefinedCalendarForAttendance;
    private boolean silentMode;

    @OneToOne(mappedBy = "setting", fetch = FetchType.EAGER)
    private Person person;
    //create a method to take de localization from professor

    //todo Setting repository


    public Setting(boolean automaticSaveLocalization, boolean usePredefinedCalendarForAttendance, boolean silentMode, Person person) {
        this.automaticSaveLocalization = automaticSaveLocalization;
        this.usePredefinedCalendarForAttendance = usePredefinedCalendarForAttendance;
        this.silentMode = silentMode;
        this.person = person;
    }
}
