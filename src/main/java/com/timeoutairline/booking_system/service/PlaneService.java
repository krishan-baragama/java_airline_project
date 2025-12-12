package com.timeoutairline.booking_system.service;

import com.timeoutairline.booking_system.model.Plane;
import com.timeoutairline.booking_system.repository.PlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaneService {
    private final PlaneRepository planeRepository;
    
    public Plane createPlane(Plane plane) {
        return planeRepository.save(plane);
    }
    
    public List<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }
    
    public Optional<Plane> getPlaneById(Long id) {
        return planeRepository.findById(id);
    }
    
    public Plane updatePlane(Long id, Plane planeDetails) {
        Plane plane = planeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plane not found with id: " + id));
        plane.setBrand(planeDetails.getBrand());
        plane.setModel(planeDetails.getModel());
        plane.setManufacturingYear(planeDetails.getManufacturingYear());
        return planeRepository.save(plane);
    }
    
    public void deletePlane(Long id) {
        if (!planeRepository.existsById(id)) {
            throw new RuntimeException("Plane not found with id: " + id);
        }
        planeRepository.deleteById(id);
    }
}