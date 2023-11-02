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
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Titulo;
    private String Descricao;
    private boolean Visivel;
    private Integer LAT;
    private Integer LON;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor Professor;

    @OneToMany(mappedBy = "localizacao")
    private List<VirtualZone> virtualZones;


}
