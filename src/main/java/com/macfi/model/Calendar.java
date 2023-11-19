package com.macfi.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "calendar")
@NoArgsConstructor
@AllArgsConstructor
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Event> events;


    public Calendar(List<Event> events) {
        this.events = events;
    }

}
