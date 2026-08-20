package com.hospital.repository;

import com.hospital.model.RoomBooking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomBookingRepository extends MongoRepository<RoomBooking, String> {
    List<RoomBooking> findByPatientId(String patientId);
    List<RoomBooking> findByRoomId(String roomId);
}
