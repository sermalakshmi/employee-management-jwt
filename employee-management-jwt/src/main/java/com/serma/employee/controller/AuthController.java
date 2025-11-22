
package com.serma.employee.controller;

import com.serma.employee.entity.AppUser;
import com.serma.employee.repository.UserRepository;
import com.serma.employee.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepo, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> body) {
        String username = body.get("username");
        String password = body.get("password");
        String role = body.getOrDefault("role","ROLE_USER");
        if(userRepo.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error","User exists"));
        }
        AppUser u = AppUser.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(role)
                .build();
        userRepo.save(u);
        return ResponseEntity.ok(Map.of("msg","registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body) {
        String username = body.get("username");
        String password = body.get("password");
        var userOpt = userRepo.findByUsername(username);
        if(userOpt.isEmpty()) return ResponseEntity.status(401).body(Map.of("error","Invalid credentials"));
        AppUser u = userOpt.get();
        if(!passwordEncoder.matches(password, u.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("error","Invalid credentials"));
        }
        String token = jwtUtil.generateToken(u.getUsername(), u.getRole());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
