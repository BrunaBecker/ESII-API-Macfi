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


    @ManyToMany
    @JoinTable(name = "calendar_event", joinColumns = @JoinColumn(name = "calendar_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"))
    @ToString.Exclude
    private List<Event> events;

    public Calendar(List<Event> events) {
        this.events = events;
    }

    public boolean addEvent(Event event) {
        return events.add(event);
    }


}
