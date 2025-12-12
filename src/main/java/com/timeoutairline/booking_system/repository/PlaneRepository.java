package com.timeoutairline.booking_system.repository;

import com.timeoutairline.booking_system.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Long> {
}