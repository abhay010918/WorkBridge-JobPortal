package com.WorkBridge.service;

import com.WorkBridge.entity.Application;
import com.WorkBridge.entity.ApplicationStatus;
import java.util.List;

public interface ApplicationService {
    Application applyForJob(Long jobId, Long userId);
    List<Application> getApplicationsByJob(Long jobId);
    List<Application> getApplicationsByUser(Long userId);
    Application updateApplicationStatus(Long applicationId, ApplicationStatus status);
}
