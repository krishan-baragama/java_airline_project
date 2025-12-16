package com.timeoutairline.booking_system.service;

import com.timeoutairline.booking_system.model.*;
import com.timeoutairline.booking_system.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingService {
    
    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final FlightRepository flightRepository;
    private final MilesRewardRepository milesRewardRepository;
    private final DiscountCodeRepository discountCodeRepository;
    private final UserRepository userRepository;
    
    public Booking createBooking(String lastname, String firstname, String passport, 
                                 LocalDate birthdate, String departureCity, String arrivalCity,
                                 String flightNumber, String typeOfSeat) {
        
        // Find or create client
        Client client = clientRepository.findByNumPassport(passport)
                .orElseGet(() -> {
                    // Create new user and client
                    User newUser = User.builder()
                            .firstname(firstname)
                            .lastname(lastname)
                            .birthdate(birthdate)
                            .build();
                    newUser = userRepository.save(newUser);
                    
                    Client newClient = Client.builder()
                            .numPassport(passport)
                            .user(newUser)
                            .build();
                    return clientRepository.save(newClient);
                });
        
        // Find flight
        Flight flight = flightRepository.findById(flightNumber)
                .orElseThrow(() -> new RuntimeException("Flight not found: " + flightNumber));
        
        // Verify flight details match
        if (!flight.getDepartureCity().equalsIgnoreCase(departureCity) ||
            !flight.getArrivalCity().equalsIgnoreCase(arrivalCity)) {
            throw new RuntimeException("Flight details do not match");
        }
        
        // Check seat availability and update
        boolean seatAvailable = false;
        switch (typeOfSeat.toUpperCase()) {
            case "FIRST":
                if (flight.getSeatsAvailableFirst() > 0) {
                    flight.setSeatsAvailableFirst(flight.getSeatsAvailableFirst() - 1);
                    seatAvailable = true;
                }
                break;
            case "PREMIUM":
                if (flight.getSeatsAvailablePremium() > 0) {
                    flight.setSeatsAvailablePremium(flight.getSeatsAvailablePremium() - 1);
                    seatAvailable = true;
                }
                break;
            case "BUSINESS":
                if (flight.getSeatsAvailableBusiness() > 0) {
                    flight.setSeatsAvailableBusiness(flight.getSeatsAvailableBusiness() - 1);
                    seatAvailable = true;
                }
                break;
            case "ECONOMY":
                if (flight.getSeatsAvailableEcon() > 0) {
                    flight.setSeatsAvailableEcon(flight.getSeatsAvailableEcon() - 1);
                    seatAvailable = true;
                }
                break;
            default:
                throw new RuntimeException("Invalid seat type: " + typeOfSeat);
        }
        
        if (!seatAvailable) {
            throw new RuntimeException("No available seats for type: " + typeOfSeat);
        }
        
        flightRepository.save(flight);
        
        // Create booking
        Booking booking = Booking.builder()
                .flight(flight)
                .client(client)
                .typeOfSeat(typeOfSeat)
                .build();
        booking = bookingRepository.save(booking);
        
        // Record in MilesReward
        MilesReward milesReward = MilesReward.builder()
                .client(client)
                .flight(flight)
                .flightDate(flight.getDepartureHour().toLocalDate())
                .build();
        milesRewardRepository.save(milesReward);
        
        // Check if client has 3 flights this year
        int currentYear = LocalDate.now().getYear();
        Long flightCount = milesRewardRepository.countFlightsByClientAndYear(client, currentYear);
        
        if (flightCount >= 3) {
            // Check if discount code already generated this year
            List<DiscountCode> existingCodes = discountCodeRepository.findByClientAndUsedFalse(client);
            boolean hasCodeThisYear = existingCodes.stream()
                    .anyMatch(dc -> dc.getCreatedAt().getYear() == currentYear);
            
            if (!hasCodeThisYear) {
                // Generate random discount code
                String code = "DISC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
                DiscountCode discountCode = DiscountCode.builder()
                        .client(client)
                        .code(code)
                        .used(false)
                        .build();
                discountCodeRepository.save(discountCode);
            }
        }
        
        return booking;
    }
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }
    
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        
        // Return seat to available pool
        Flight flight = booking.getFlight();
        String seatType = booking.getTypeOfSeat();
        
        switch (seatType.toUpperCase()) {
            case "FIRST":
                flight.setSeatsAvailableFirst(flight.getSeatsAvailableFirst() + 1);
                break;
            case "PREMIUM":
                flight.setSeatsAvailablePremium(flight.getSeatsAvailablePremium() + 1);
                break;
            case "BUSINESS":
                flight.setSeatsAvailableBusiness(flight.getSeatsAvailableBusiness() + 1);
                break;
            case "ECONOMY":
                flight.setSeatsAvailableEcon(flight.getSeatsAvailableEcon() + 1);
                break;
        }
        
        flightRepository.save(flight);
        bookingRepository.deleteById(id);
    }
}