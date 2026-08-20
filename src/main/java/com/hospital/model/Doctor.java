package com.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "doctors")
public class Doctor {
    @Id
    private String id;
    private String name;
    private String specialization;
    private String phone;
    private String email;
    private List<Schedule> schedules;
    private boolean isActive;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Schedule {
        private String dayOfWeek;
        private String startTime; // format HH:mm
        private String endTime;
    }
}
