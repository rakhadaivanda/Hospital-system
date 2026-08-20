package com.hospital.controller.web;

import com.hospital.model.Patient;
import com.hospital.model.Room;
import com.hospital.repository.PatientRepository;
import com.hospital.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PatientRepository patientRepository;
    private final RoomRepository roomRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalPatients", patientRepository.count());
        model.addAttribute("totalRooms", roomRepository.count());
        return "admin/dashboard";
    }

    // Rooms Management
    @GetMapping("/rooms")
    public String rooms(Model model) {
        model.addAttribute("rooms", roomRepository.findAll());
        return "admin/rooms";
    }

    @PostMapping("/rooms/add")
    public String addRoom(@ModelAttribute Room room) {
        roomRepository.save(room);
        return "redirect:/admin/rooms";
    }
    
    // Patients Management
    @GetMapping("/patients")
    public String patients(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "admin/patients";
    }

    // Pharmacy Management
    @GetMapping("/pharmacy")
    public String pharmacy(Model model) {
        return "admin/pharmacy";
    }

    // Billing Management
    @GetMapping("/billing")
    public String billing(Model model) {
        return "admin/billing";
    }
}
