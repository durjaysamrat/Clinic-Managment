package com.app.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.app.model.Doctor;
import com.app.service.DoctorusersService;
import java.util.Optional;

@CrossOrigin(origins = "*") // Allow requests from all origins
@RestController
@RequestMapping("/api/doctoruser") 
public class DoctorusersController {

	
	private final DoctorusersService doctorusersService;

    public DoctorusersController(DoctorusersService doctorusersService) {
        this.doctorusersService = doctorusersService;
    }

    // ✅ Get All Users
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllUsers() {
        List<Doctor> desks = doctorusersService.getAllUsers();
        return ResponseEntity.ok(desks);
    }

    // ✅ Get User By ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getUserById(@PathVariable Long id) {
        Optional<Doctor> desks = doctorusersService.getUserById(id);
        return desks.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Add New User
    @PostMapping
    public ResponseEntity<Doctor> addUser(@RequestBody Doctor doctor) {
        Doctor savedUser = doctorusersService.addUser(doctor);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateUser(@PathVariable Long id, @RequestBody Doctor updatedUser) {
        Optional<Doctor> user = doctorusersService.getUserById(id);
        if (user.isPresent()) {
            updatedUser.setId(id);
            Doctor savedUser = doctorusersService.updateUser(updatedUser);
            return ResponseEntity.ok(savedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = doctorusersService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}