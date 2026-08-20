package com.hospital.controller.web;

import com.hospital.model.Appointment;
import com.hospital.model.User;
import com.hospital.repository.AppointmentRepository;
import com.hospital.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final UserService userService;
    private final AppointmentRepository appointmentRepository;

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userService.findByUsername(username).orElse(null);
        model.addAttribute("user", user);
        return "patient/dashboard";
    }

    @GetMapping("/appointments")
    public String appointments(Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userService.findByUsername(username).orElse(null);
        model.addAttribute("appointments", appointmentRepository.findAll().stream()
                .filter(a -> a.getPatientId() != null && a.getPatientId().equals(user.getId()))
                .toList());
        return "patient/appointments";
    }

    @GetMapping("/appointments/book")
    public String bookAppointment() {
        return "patient/book_appointment";
    }

    @PostMapping("/appointments/book")
    public String submitAppointment(
            @RequestParam String doctorId,
            @RequestParam String appointmentDate,
            @RequestParam String notes,
            Authentication authentication) {
        
        String username = authentication.getName();
        User user = userService.findByUsername(username).orElse(null);

        Appointment appointment = Appointment.builder()
                .patientId(user.getId())
                .doctorId(doctorId)
                .appointmentDate(LocalDate.parse(appointmentDate))
                .appointmentTime(LocalTime.of(9, 0)) // dummy time
                .status("REQUESTED")
                .notes(notes)
                .build();
        
        appointmentRepository.save(appointment);
        return "redirect:/patient/appointments";
    }

    @GetMapping("/rooms")
    public String rooms() {
        return "patient/rooms";
    }

    @GetMapping("/prescriptions")
    public String prescriptions() {
        return "patient/prescriptions";
    }
}
