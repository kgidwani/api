package com.bizenlabs.api.controllers;

import com.bizenlabs.api.ollama.OllamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ollama")
public class OllamaController {

    @Autowired
    OllamaService ollamaService;


    @PostMapping("/service/start")
    public ResponseEntity<String> startOllamaService() {
        long pid = ollamaService.startOllamaService();
        return ResponseEntity.ok().body("Started ollama service with PID: " + pid);
    }

    @GetMapping({"/download/release/"})
    public ResponseEntity<String> downloadLatestRelease() {
        return ResponseEntity.accepted().body(ollamaService.downloadOllama());
    }

    // Downloaded releases
    // List available releases
    // Download specific release
    // Delete specific release
    // Get status of ollama service
    // Start ollama service
    // Stop ollama service
    // Restart ollama service
    // Get logs of ollama service
    // Clear logs of ollama service
    // Get version of ollama service
    // Update ollama service
    // Rollback ollama service
    // Get configuration of ollama service
    // Update configuration of ollama service
    // Reset configuration of ollama service
    // Get models of ollama service
    // Add model to ollama service
    // Remove model from ollama service
    // Update model in ollama service
    // Get conversations of ollama service
    // Delete conversation from ollama service
    // Clear all conversations from ollama service
    // Export conversations from ollama service
    // Import conversations to ollama service
    // Get usage statistics of ollama service
    // Clear usage statistics of ollama service
    // Get help for ollama service
    // Get about info for ollama service
    // Health check for ollama service
    // Metrics for ollama service
    // Get system info for ollama service
    // Get license info for ollama service
    // Update license info for ollama service
    // Validate license info for ollama service
    // Revoke license info for ollama service

}
