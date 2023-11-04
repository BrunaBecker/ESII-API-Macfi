package com.macfi.model;


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

    @OneToOne
    private Person person;
    //create a method to take de localization from professor

    //todo Setting repository


    public Setting(Long id, boolean automaticSaveLocalization, boolean usePredefinedCalendarForAttendance, boolean silentMode, Person person) {
        this.id = id;
        this.automaticSaveLocalization = automaticSaveLocalization;
        this.usePredefinedCalendarForAttendance = usePredefinedCalendarForAttendance;
        this.silentMode = silentMode;
        this.person = person;
    }
}
