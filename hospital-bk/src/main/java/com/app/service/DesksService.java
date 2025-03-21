package com.app.service;

import com.app.model.Desks;
import com.app.repo.DesksRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DesksService {
	private final DesksRepository desksRepository;

    public DesksService(DesksRepository desksRepository) {
        this.desksRepository = desksRepository;
    }

    public List<Desks> getAllUsers() {
        return desksRepository.findAll();
    }

    public Optional<Desks> getUserById(Long id) {
        return desksRepository.findById(id);
    }

    public Desks addUser(Desks desks) {
        return desksRepository.save(desks);
    }

    public Desks updateUser(Desks Desk) {
        return desksRepository.save(Desk);
    }

    public boolean deleteUser(Long id) {
        if (desksRepository.existsById(id)) {
            desksRepository.deleteById(id);
            return true;  
        } else {
            return false;
        }
    }
}
