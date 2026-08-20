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
@Document(collection = "bills")
public class Bill {
    @Id
    private String id;
    private String patientId;
    private LocalDate billDate;
    private LocalDate dueDate;
    private List<BillItem> items;
    private double totalAmount;
    private double paidAmount;
    private String status; // UNPAID, PARTIAL, PAID

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BillItem {
        private String description;
        private double amount;
        private String category; // ROOM, MEDICINE, CONSULTATION, OTHER
    }
}
