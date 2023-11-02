package com.macfi.model;

import com.macfi.model.utils.Coordinates;
import com.macfi.model.utils.GeoLocation;
import com.macfi.model.utils.enums_class.StatusPing;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String ip;

    private Date date;

    private StatusPing status;

    private boolean isContinuos;

    @OneToOne
    private Coordinates coordinates;

    @ManyToOne
    private AttendanceStatus attendanceStatus;

    public boolean inCorrectLocation(double lat1, double lon1, double lat2, double lon2) {
        return GeoLocation.inRadiusMacfi(lat1, lon1, lat2, lon2);
    }

}
