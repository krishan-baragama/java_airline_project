package com.timeoutairline.booking_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emp")
    private Long idEmp;

    @NotBlank(message = "Employee number is required")
    @Column(name = "num_emp", unique = true, length = 50, nullable = false)
    private String numEmp;

    @Column(name = "profession", length = 100)
    private String profession;

    @Column(name = "title", length = 100)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}