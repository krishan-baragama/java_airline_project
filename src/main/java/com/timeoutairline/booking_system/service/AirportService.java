// AirportService.java
package com.timeoutairline.booking_system.service;

import com.timeoutairline.booking_system.model.Airport;
import com.timeoutairline.booking_system.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AirportService {
    private final AirportRepository airportRepository;
    
    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }
    
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }
    
    public Optional<Airport> getAirportById(Long id) {
        return airportRepository.findById(id);
    }
    
    public Airport updateAirport(Long id, Airport airportDetails) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found with id: " + id));
        airport.setNameAirport(airportDetails.getNameAirport());
        airport.setCountryAirport(airportDetails.getCountryAirport());
        airport.setCityAirport(airportDetails.getCityAirport());
        return airportRepository.save(airport);
    }
    
    public void deleteAirport(Long id) {
        if (!airportRepository.existsById(id)) {
            throw new RuntimeException("Airport not found with id: " + id);
        }
        airportRepository.deleteById(id);
    }
}