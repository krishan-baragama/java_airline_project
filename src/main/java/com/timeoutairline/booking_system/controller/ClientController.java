package com.timeoutairline.booking_system.controller;

import com.timeoutairline.booking_system.model.Client;
import com.timeoutairline.booking_system.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClientController {
    
    private final ClientService clientService;
    
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Map<String, Object> request) {
        Client client = new Client();
        client.setNumPassport((String) request.get("numPassport"));
        Long userId = Long.valueOf(request.get("userId").toString());
        
        Client createdClient = clientService.createClient(client, userId);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/passport/{passport}")
    public ResponseEntity<Client> getClientByPassport(@PathVariable String passport) {
        return clientService.getClientByPassport(passport)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        try {
            Client updatedClient = clientService.updateClient(id, client);
            return ResponseEntity.ok(updatedClient);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}