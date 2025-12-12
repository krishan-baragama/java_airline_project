package com.timeoutairline.booking_system.service;

import com.timeoutairline.booking_system.model.Flight;
import com.timeoutairline.booking_system.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightService {
    private final FlightRepository flightRepository;
    
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }
    
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
    
    public Optional<Flight> getFlightByNumber(String flightNumber) {
        return flightRepository.findById(flightNumber);
    }
    
    public Flight updateFlight(String flightNumber, Flight flightDetails) {
        Flight flight = flightRepository.findById(flightNumber)
                .orElseThrow(() -> new RuntimeException("Flight not found: " + flightNumber));
        
        flight.setDepartureCity(flightDetails.getDepartureCity());
        flight.setArrivalCity(flightDetails.getArrivalCity());
        flight.setDepartureHour(flightDetails.getDepartureHour());
        flight.setArrivalHour(flightDetails.getArrivalHour());
        flight.setDepartureAirport(flightDetails.getDepartureAirport());
        flight.setArrivalAirport(flightDetails.getArrivalAirport());
        flight.setPlane(flightDetails.getPlane());
        flight.setNumberOfSeat(flightDetails.getNumberOfSeat());
        flight.setFirstClassSeatPrice(flightDetails.getFirstClassSeatPrice());
        flight.setPremiumSeatPrice(flightDetails.getPremiumSeatPrice());
        flight.setBusinessClassPrice(flightDetails.getBusinessClassPrice());
        flight.setEconClassPrice(flightDetails.getEconClassPrice());
        flight.setSeatsAvailableFirst(flightDetails.getSeatsAvailableFirst());
        flight.setSeatsAvailablePremium(flightDetails.getSeatsAvailablePremium());
        flight.setSeatsAvailableBusiness(flightDetails.getSeatsAvailableBusiness());
        flight.setSeatsAvailableEcon(flightDetails.getSeatsAvailableEcon());
        
        return flightRepository.save(flight);
    }
    
    public void deleteFlight(String flightNumber) {
        if (!flightRepository.existsById(flightNumber)) {
            throw new RuntimeException("Flight not found: " + flightNumber);
        }
        flightRepository.deleteById(flightNumber);
    }
    
    public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDateTime departureDate) {
        return flightRepository.searchFlights(departureCity, arrivalCity, departureDate);
    }
}
