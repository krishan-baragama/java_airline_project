package com.timeoutairline.booking_system.repository;

import com.timeoutairline.booking_system.model.DiscountCode;
import com.timeoutairline.booking_system.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {
    List<DiscountCode> findByClientAndUsedFalse(Client client);
}