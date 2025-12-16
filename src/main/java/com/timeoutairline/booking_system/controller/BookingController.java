package com.timeoutairline.booking_system.controller;

import com.timeoutairline.booking_system.model.Booking;
import com.timeoutairline.booking_system.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookingController {
    
    private final BookingService bookingService;
    
    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Map<String, String> bookingRequest) {
        try {
            String lastname = bookingRequest.get("lastname");
            String firstname = bookingRequest.get("firstname");
            String passport = bookingRequest.get("passport");
            LocalDate birthdate = LocalDate.parse(bookingRequest.get("birthdate"));
            String departureCity = bookingRequest.get("departureCity");
            String arrivalCity = bookingRequest.get("arrivalCity");
            String flightNumber = bookingRequest.get("flightNumber");
            String typeOfSeat = bookingRequest.get("typeOfSeat");
            
            Booking booking = bookingService.createBooking(
                    lastname, firstname, passport, birthdate,
                    departureCity, arrivalCity, flightNumber, typeOfSeat
            );
            
            return new ResponseEntity<>(booking, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        try {
            bookingService.deleteBooking(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}