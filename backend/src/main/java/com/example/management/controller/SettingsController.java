package com.example.management.controller;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/settings")
@CrossOrigin(origins = "*")
public class SettingsController {
    
    private final Map<String, Object> settings = new ConcurrentHashMap<>();
    
    public SettingsController() {
        settings.put("systemName", "管理系统");
        settings.put("systemDesc", "企业级管理系统");
        settings.put("defaultRole", "user");
        settings.put("minPasswordLength", 6);
        settings.put("twoFactorAuth", false);
        settings.put("sessionTimeout", 30);
        settings.put("emailNotify", false);
        settings.put("smsNotify", false);
    }
    
    @GetMapping
    public Map<String, Object> getSettings() {
        return new HashMap<>(settings);
    }
    
    @PostMapping("/basic")
    public Map<String, Object> saveBasicSettings(@RequestBody Map<String, Object> data) {
        if (data.containsKey("systemName")) settings.put("systemName", data.get("systemName"));
        if (data.containsKey("systemDesc")) settings.put("systemDesc", data.get("systemDesc"));
        return Map.of("success", true, "message", "基本设置已保存");
    }
    
    @PostMapping("/user")
    public Map<String, Object> saveUserSettings(@RequestBody Map<String, Object> data) {
        if (data.containsKey("defaultRole")) settings.put("defaultRole", data.get("defaultRole"));
        if (data.containsKey("minPasswordLength")) settings.put("minPasswordLength", data.get("minPasswordLength"));
        return Map.of("success", true, "message", "用户设置已保存");
    }
    
    @PostMapping("/security")
    public Map<String, Object> saveSecuritySettings(@RequestBody Map<String, Object> data) {
        if (data.containsKey("twoFactorAuth")) settings.put("twoFactorAuth", data.get("twoFactorAuth"));
        if (data.containsKey("sessionTimeout")) settings.put("sessionTimeout", data.get("sessionTimeout"));
        return Map.of("success", true, "message", "安全设置已保存");
    }
    
    @PostMapping("/notify")
    public Map<String, Object> saveNotifySettings(@RequestBody Map<String, Object> data) {
        if (data.containsKey("emailNotify")) settings.put("emailNotify", data.get("emailNotify"));
        if (data.containsKey("smsNotify")) settings.put("smsNotify", data.get("smsNotify"));
        return Map.of("success", true, "message", "通知设置已保存");
    }
}