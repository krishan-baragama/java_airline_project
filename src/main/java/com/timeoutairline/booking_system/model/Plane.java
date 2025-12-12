package com.timeoutairline.booking_system.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "planes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plane {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plane")
    private Long idPlane;
    
    @Column(name = "brand", length = 100)
    private String brand;
    
    @Column(name = "model", length = 100)
    private String model;
    
    @Column(name = "manufacturing_year")
    private Integer manufacturingYear;
}
