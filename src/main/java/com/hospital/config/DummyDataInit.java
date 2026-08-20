package com.hospital.config;

import com.hospital.model.Medicine;
import com.hospital.model.Patient;
import com.hospital.model.Role;
import com.hospital.model.Room;
import com.hospital.model.User;
import com.hospital.repository.MedicineRepository;
import com.hospital.repository.PatientRepository;
import com.hospital.repository.RoomRepository;
import com.hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class DummyDataInit {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final MedicineRepository medicineRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            try {
                // Create Admin User if not exists
                if (userRepository.findByUsername("admin").isEmpty()) {
                    User admin = User.builder()
                            .username("admin")
                            .password(passwordEncoder.encode("admin123"))
                            .role(Role.ADMIN)
                            .enabled(true)
                            .build();
                    userRepository.save(admin);
                    System.out.println("[DummyDataInit] Admin user created.");
                }

                // Create Patient User if not exists
                if (userRepository.findByUsername("patient").isEmpty()) {
                    User patient = User.builder()
                            .username("patient")
                            .password(passwordEncoder.encode("patient123"))
                            .role(Role.PATIENT)
                            .enabled(true)
                            .build();
                    userRepository.save(patient);
                    System.out.println("[DummyDataInit] Patient user created.");
                }

                // Create Rooms if empty
                if (roomRepository.count() == 0) {
                    roomRepository.save(Room.builder().roomNumber("101").type("VIP").pricePerNight(1500000).status("AVAILABLE").floor("1").description("Premium VIP Room with sea view").build());
                    roomRepository.save(Room.builder().roomNumber("102").type("CLASS_1").pricePerNight(800000).status("AVAILABLE").floor("1").description("Class 1 Standard Room").build());
                    roomRepository.save(Room.builder().roomNumber("103").type("ICU").pricePerNight(2500000).status("OCCUPIED").floor("2").description("Intensive Care Unit").build());
                    System.out.println("[DummyDataInit] Rooms created.");
                }

                // Create Medicines if empty
                if (medicineRepository.count() == 0) {
                    medicineRepository.save(Medicine.builder().name("Paracetamol").category("Painkiller").description("Fever and mild pain").price(5000).stock(150).unit("Tablet").supplier("PharmaInc").expiryDate(LocalDate.now().plusYears(1)).minimumStock(20).build());
                    medicineRepository.save(Medicine.builder().name("Amoxicillin").category("Antibiotic").description("Bacterial infection").price(12000).stock(80).unit("Capsule").supplier("HealthMed").expiryDate(LocalDate.now().plusYears(2)).minimumStock(10).build());
                    System.out.println("[DummyDataInit] Medicines created.");
                }

                // Create Patients if empty
                if (patientRepository.count() == 0) {
                    patientRepository.save(Patient.builder().medicalRecordNumber("MRN-001").name("Budi Santoso").gender("Male").dateOfBirth(LocalDate.of(1990, 5, 15)).phone("08123456789").address("Jl. Merdeka No. 10").bloodType("O").build());
                    patientRepository.save(Patient.builder().medicalRecordNumber("MRN-002").name("Siti Aminah").gender("Female").dateOfBirth(LocalDate.of(1985, 10, 20)).phone("08987654321").address("Jl. Sudirman No. 5").bloodType("A").build());
                    System.out.println("[DummyDataInit] Patients created.");
                }

                System.out.println("[DummyDataInit] Initialization complete.");
            } catch (Exception e) {
                System.err.println("[DummyDataInit] WARNING: Could not initialize data - " + e.getMessage());
                System.err.println("[DummyDataInit] Application will continue without dummy data. Check MongoDB connection.");
            }
        };
    }
}
