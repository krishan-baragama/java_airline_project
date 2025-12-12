// AirportRepository.java
package com.timeoutairline.booking_system.repository;

import com.timeoutairline.booking_system.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    List<Airport> findByCityAirport(String city);
}