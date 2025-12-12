package com.timeoutairline.booking_system.controller;

import com.timeoutairline.booking_system.model.Flight;
import com.timeoutairline.booking_system.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FlightController {
    
    private final FlightService flightService;
    
    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight createdFlight = flightService.createFlight(flight);
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }
    
    @GetMapping("/{flightNumber}")
    public ResponseEntity<Flight> getFlightByNumber(@PathVariable String flightNumber) {
        return flightService.getFlightByNumber(flightNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{flightNumber}")
    public ResponseEntity<Flight> updateFlight(@PathVariable String flightNumber, @RequestBody Flight flight) {
        try {
            Flight updatedFlight = flightService.updateFlight(flightNumber, flight);
            return ResponseEntity.ok(updatedFlight);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{flightNumber}")
    public ResponseEntity<Void> deleteFlight(@PathVariable String flightNumber) {
        try {
            flightService.deleteFlight(flightNumber);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDate) {
        
        List<Flight> flights = flightService.searchFlights(departureCity, arrivalCity, departureDate);
        return ResponseEntity.ok(flights);
    }
}