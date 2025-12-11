package com.timeoutairline.booking_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    
    @NotBlank(message = "Firstname is required")
    @Column(name = "firstname", length = 100)
    private String firstname;
    
    @NotBlank(message = "Lastname is required")
    @Column(name = "lastname", length = 100)
    private String lastname;
    
    @Column(name = "address")
    private String address;
    
    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true, length = 150)
    private String email;
    
    @Column(name = "phone", length = 50)
    private String phone;
    
    @Column(name = "birthdate")
    private LocalDate birthdate;
}