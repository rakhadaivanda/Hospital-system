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
@Document(collection = "room_bookings")
public class RoomBooking {
    @Id
    private String id;
    private String patientId;
    private String roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status; // REQUESTED, BOOKED, CHECKED_IN, CHECKED_OUT, CANCELLED
    private double totalCost;
    private String notes;
}
