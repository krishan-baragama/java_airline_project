package com.timeoutairline.booking_system.controller;

import com.timeoutairline.booking_system.model.Plane;
import com.timeoutairline.booking_system.service.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/planes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlaneController {
    
    private final PlaneService planeService;
    
    @PostMapping
    public ResponseEntity<Plane> createPlane(@RequestBody Plane plane) {
        Plane createdPlane = planeService.createPlane(plane);
        return new ResponseEntity<>(createdPlane, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Plane>> getAllPlanes() {
        List<Plane> planes = planeService.getAllPlanes();
        return ResponseEntity.ok(planes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Plane> getPlaneById(@PathVariable Long id) {
        return planeService.getPlaneById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Plane> updatePlane(@PathVariable Long id, @RequestBody Plane plane) {
        try {
            Plane updatedPlane = planeService.updatePlane(id, plane);
            return ResponseEntity.ok(updatedPlane);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlane(@PathVariable Long id) {
        try {
            planeService.deletePlane(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
