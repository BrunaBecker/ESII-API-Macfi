package com.chamada.macfi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import com.chamada.macfi.model.person.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Localization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean isVisible;
    private Integer LAT;
    private Integer LON;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToMany(mappedBy = "localizacao")
    private List<VirtualZone> virtualZones;


}
