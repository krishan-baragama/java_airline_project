package com.timeoutairline.booking_system.repository;

import com.timeoutairline.booking_system.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    @Query("SELECT f FROM Flight f WHERE f.departureCity = :departureCity " +
           "AND f.arrivalCity = :arrivalCity " +
           "AND DATE(f.departureHour) = DATE(:departureDate)")
    List<Flight> searchFlights(@Param("departureCity") String departureCity,
                               @Param("arrivalCity") String arrivalCity,
                               @Param("departureDate") LocalDateTime departureDate);
} 
