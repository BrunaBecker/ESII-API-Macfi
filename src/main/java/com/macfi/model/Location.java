package com.macfi.model;

import com.macfi.model.person.Professor;
import com.macfi.model.utils.Coordinates;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean isActive;

    @OneToOne
    private Coordinates coordinates;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToMany(mappedBy = "location")
    private List<VirtualZone> virtualZones;


}
