package com.timeoutairline.booking_system.repository;

import com.timeoutairline.booking_system.model.MilesReward;
import com.timeoutairline.booking_system.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MilesRewardRepository extends JpaRepository<MilesReward, Long> {
    @Query("SELECT COUNT(m) FROM MilesReward m WHERE m.client = :client " +
           "AND YEAR(m.flightDate) = :year")
    Long countFlightsByClientAndYear(@Param("client") Client client, @Param("year") int year);
}