package com.WorkBridge.controller;

import com.WorkBridge.entity.Application;
import com.WorkBridge.entity.ApplicationStatus;
import com.WorkBridge.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // ✅ Apply for a Job
    @PostMapping("/apply/{jobId}/{userId}")
    public ResponseEntity<Application> applyForJob(@PathVariable Long jobId, @PathVariable Long userId) {
        Application application = applicationService.applyForJob(jobId, userId);
        return ResponseEntity.ok(application);
    }

    // ✅ Get Applications by Job
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Application>> getApplicationsByJob(@PathVariable Long jobId) {
        List<Application> applications = applicationService.getApplicationsByJob(jobId);
        return ResponseEntity.ok(applications);
    }

    // ✅ Get Applications by User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Application>> getApplicationsByUser(@PathVariable Long userId) {
        List<Application> applications = applicationService.getApplicationsByUser(userId);
        return ResponseEntity.ok(applications);
    }

    // ✅ Update Application Status
    @PutMapping("/status/{applicationId}")
    public ResponseEntity<Application> updateApplicationStatus(@PathVariable Long applicationId, @RequestParam ApplicationStatus status) {
        Application updatedApplication = applicationService.updateApplicationStatus(applicationId, status);
        return ResponseEntity.ok(updatedApplication);
    }
}
