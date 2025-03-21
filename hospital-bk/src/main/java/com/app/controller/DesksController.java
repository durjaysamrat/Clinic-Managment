package com.app.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.app.model.Desks;
import com.app.service.DesksService;
import java.util.Optional;

@CrossOrigin(origins = "*") // Allow requests from all origins
@RestController
@RequestMapping("/api/desks") 
public class DesksController {

	
	private final DesksService desksService;

    public DesksController(DesksService desksService) {
        this.desksService = desksService;
    }

    // ✅ Get All Users
    @GetMapping
    public ResponseEntity<List<Desks>> getAllUsers() {
        List<Desks> desks = desksService.getAllUsers();
        return ResponseEntity.ok(desks);
    }

    // ✅ Get User By ID
    @GetMapping("/{id}")
    public ResponseEntity<Desks> getUserById(@PathVariable Long id) {
        Optional<Desks> desks = desksService.getUserById(id);
        return desks.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Add New User
    @PostMapping
    public ResponseEntity<Desks> addUser(@RequestBody Desks desks) {
        Desks savedUser = desksService.addUser(desks);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Desks> updateUser(@PathVariable Long id, @RequestBody Desks updatedUser) {
        Optional<Desks> user = desksService.getUserById(id);
        if (user.isPresent()) {
            updatedUser.setId(id);
            Desks savedUser = desksService.updateUser(updatedUser);
            return ResponseEntity.ok(savedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = desksService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

