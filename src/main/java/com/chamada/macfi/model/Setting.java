package com.chamada.macfi.model;


import com.chamada.macfi.model.person.Pessoa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean automaticSaveLocalization;
    private boolean usePredefinedCalendarForAttendance;
    private boolean silentMode;

    @OneToOne
    private Pessoa person;
    //create a method to take de localization from professor
}
