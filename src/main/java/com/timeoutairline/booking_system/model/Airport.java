package com.timeoutairline.booking_system.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "airports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_airport")
    private Long idAirport;
    
    @Column(name = "name_airport", length = 150)
    private String nameAirport;
    
    @Column(name = "country_airport", length = 100)
    private String countryAirport;
    
    @Column(name = "city_airport", length = 100)
    private String cityAirport;
}