package com.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rooms")
public class Room {
    @Id
    private String id;
    private String roomNumber;
    private String type; // VIP, CLASS_1, CLASS_2, CLASS_3, ICU
    private double pricePerNight;
    private String status; // AVAILABLE, OCCUPIED, MAINTENANCE
    private String floor;
    private String description;
}
