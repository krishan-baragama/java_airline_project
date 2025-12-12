// ClientService.java
package com.timeoutairline.booking_system.service;

import com.timeoutairline.booking_system.model.Client;
import com.timeoutairline.booking_system.model.User;
import com.timeoutairline.booking_system.repository.ClientRepository;
import com.timeoutairline.booking_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    
    public Client createClient(Client client, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        client.setUser(user);
        return clientRepository.save(client);
    }
    
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }
    
    public Optional<Client> getClientByPassport(String passport) {
        return clientRepository.findByNumPassport(passport);
    }
    
    public Client updateClient(Long id, Client clientDetails) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        client.setNumPassport(clientDetails.getNumPassport());
        if (clientDetails.getUser() != null) {
            client.setUser(clientDetails.getUser());
        }
        return clientRepository.save(client);
    }
    
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }
}