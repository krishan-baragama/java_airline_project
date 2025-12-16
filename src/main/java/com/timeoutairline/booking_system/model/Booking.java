package com.timeoutairline.booking_system.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long idReservation;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_number")
    private Flight flight;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;
    
    @Column(name = "type_of_seat", length = 50)
    private String typeOfSeat;
    
    @Column(name = "booking_date")
    private LocalDateTime bookingDate;
    
    @PrePersist
    protected void onCreate() {
        bookingDate = LocalDateTime.now();
    }
}