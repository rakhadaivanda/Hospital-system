package com.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "patients")
public class Patient {
    @Id
    private String id;
    private String medicalRecordNumber;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String bloodType;
    private String address;
    private String phone;
    private String email;
    private String medicalHistory;
    private LocalDate registrationDate;
    private boolean isActive;
}
