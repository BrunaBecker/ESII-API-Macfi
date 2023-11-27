package com.macfi.model;

import com.macfi.model.utils.Coordinate;
import com.macfi.model.utils.GeoLocation;
import com.macfi.model.utils.enums_class.StatusPing;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "ping")
public class Ping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String ip;


    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private StatusPing status;

    private Boolean isContinuos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate_id", referencedColumnName = "id")
    private Coordinate coordinate;

    @ManyToOne
    @JoinColumn(name = "attendance_status_id", referencedColumnName = "id")
    private AttendanceStatus attendanceStatus;

    public Ping(String s, Date date, StatusPing statusPing, boolean b, Coordinate coordinate, AttendanceStatus attendanceStatus) {
        this.ip = s;
        this.date = date;
        this.status = statusPing;
        this.isContinuos = b;
        this.coordinate = coordinate;
        this.attendanceStatus = attendanceStatus;
    }

    public boolean inCorrectLocation(double lat1, double lon1, double lat2, double lon2) {
        try {
            return GeoLocation.inRadiusMacfi(lat1, lon1, lat2, lon2);
        } catch (Exception e) {
            throw new RuntimeException("Error inCorrectLocation " + e.getMessage());
        }

    }
}
