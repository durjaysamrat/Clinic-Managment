package com.app.service;

import com.app.model.User;
import com.app.repo.adminloginrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class adminloginservices {
    @Autowired
    private adminloginrepo adminLoginRepo;

    public boolean validateUser(String username, String password) {
        Optional<User> userOptional = adminLoginRepo.findByUsername(username);
        return userOptional.isPresent() && userOptional.get().getPassword().equals(password);
    }
}
