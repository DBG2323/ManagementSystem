package com.example.management.controller;

import com.example.management.entity.User;
import com.example.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserService userService;
    
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        
        User user = userService.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            Map<String, Object> response = new HashMap<>();
            response.put("token", "mock-jwt-token-" + username);
            response.put("user", user);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(Map.of("message", "用户名或密码错误"));
    }
    
    @GetMapping("/dashboard/stats")
    public ResponseEntity<?> getStats() {
        List<User> users = userService.getAllUsers();
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", users.size());
        stats.put("activeUsers", users.size() - 1);
        stats.put("orders", 89);
        stats.put("revenue", 12345);
        return ResponseEntity.ok(stats);
    }
    
    @GetMapping("/users")
    public ResponseEntity<?> getUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<User> users = userService.getAllUsers();
        Map<String, Object> response = new HashMap<>();
        response.put("data", users);
        response.put("total", users.size());
        response.put("page", page);
        return ResponseEntity.ok(response);
    }
}