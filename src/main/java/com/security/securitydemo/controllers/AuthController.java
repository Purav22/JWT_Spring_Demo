package com.security.securitydemo.controllers;


import com.security.securitydemo.Repo.RoleRepository;
import com.security.securitydemo.Repo.UserRepository;
import com.security.securitydemo.dto.AuthResponse;
import com.security.securitydemo.dto.LoginRequest;
import com.security.securitydemo.dto.RegisterRequest;
import com.security.securitydemo.entity.Role;
import com.security.securitydemo.entity.User;
import com.security.securitydemo.jwtconfig.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
        if (userRepo.findByUsername(req.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken");
        }

        Role role = roleRepo.findByName("ROLE_USER").orElseGet(() -> roleRepo.save(new Role(null, "ROLE_USER")));
        User user = new User(null, req.getUsername(), passwordEncoder.encode(req.getPassword()), Set.of(role));
        userRepo.save(user);

        System.out.println("HELLO");
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
    	 Authentication authentication = authManager.authenticate(
                 new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
         );

         SecurityContextHolder.getContext().setAuthentication(authentication);
         UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         String token = jwtUtils.generateToken(userDetails);

         return ResponseEntity.ok(new AuthResponse(token));
    }
}
