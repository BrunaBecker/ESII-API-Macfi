package com.macfi.model;

import com.macfi.model.person.Professor;
import com.macfi.model.utils.Coordinate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//import org.locationtech.jts.geom.Coordinates;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate_id", referencedColumnName = "id")
    private Coordinate coordinate;

    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private Professor professor;

    @OneToMany(mappedBy = "location")
    @ToString.Exclude
    private List<VirtualZone> virtualZones;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    private Classroom classroom;

    public Location(Long id, String title, String description, boolean isActive, Coordinate coordinate, Professor professor, List<VirtualZone> virtualZones) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isActive = isActive;
        this.coordinate = coordinate;
        this.professor = professor;
        this.virtualZones = virtualZones;
    }


}
