package com.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "prescriptions")
public class Prescription {
    @Id
    private String id;
    private String patientId;
    private String doctorId;
    private LocalDate prescriptionDate;
    private List<PrescriptionItem> items;
    private String notes;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PrescriptionItem {
        private String medicineId;
        private String medicineName;
        private int quantity;
        private String dosage;
        private String instructions;
    }
}
