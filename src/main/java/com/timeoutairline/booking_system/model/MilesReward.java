package com.timeoutairline.booking_system.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "miles_reward")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MilesReward {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_number")
    private Flight flight;
    
    @Column(name = "flight_date")
    private LocalDate flightDate;
}