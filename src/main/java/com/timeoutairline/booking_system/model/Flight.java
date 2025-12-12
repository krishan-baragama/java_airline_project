package com.timeoutairline.booking_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
    
    @Id
    @Column(name = "flight_number", length = 50)
    private String flightNumber;
    
    @Column(name = "departure_city", length = 100)
    private String departureCity;
    
    @Column(name = "arrival_city", length = 100)
    private String arrivalCity;
    
    @Column(name = "departure_hour")
    private LocalDateTime departureHour;
    
    @Column(name = "arrival_hour")
    private LocalDateTime arrivalHour;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_plane")
    private Plane plane;
    
    @Column(name = "number_of_seat")
    private Integer numberOfSeat;
    
    @Column(name = "first_class_seat_price", precision = 10, scale = 2)
    private BigDecimal firstClassSeatPrice;
    
    @Column(name = "premium_seat_price", precision = 10, scale = 2)
    private BigDecimal premiumSeatPrice;
    
    @Column(name = "business_class_price", precision = 10, scale = 2)
    private BigDecimal businessClassPrice;
    
    @Column(name = "econ_class_price", precision = 10, scale = 2)
    private BigDecimal econClassPrice;
    
    @Column(name = "seats_available_first")
    private Integer seatsAvailableFirst;
    
    @Column(name = "seats_available_premium")
    private Integer seatsAvailablePremium;
    
    @Column(name = "seats_available_business")
    private Integer seatsAvailableBusiness;
    
    @Column(name = "seats_available_econ")
    private Integer seatsAvailableEcon;
}
