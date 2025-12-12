// ClientRepository.java
package com.timeoutairline.booking_system.repository;

import com.timeoutairline.booking_system.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByNumPassport(String numPassport);
}