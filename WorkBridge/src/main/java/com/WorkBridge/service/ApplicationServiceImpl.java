package com.WorkBridge.service;


import com.WorkBridge.entity.AppUser;
import com.WorkBridge.entity.Application;
import com.WorkBridge.entity.ApplicationStatus;
import com.WorkBridge.entity.Job;
import com.WorkBridge.repository.AppUserRepository;
import com.WorkBridge.repository.ApplicationRepository;
import com.WorkBridge.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final AppUserRepository appUserRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, JobRepository jobRepository, AppUserRepository appUserRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Application applyForJob(Long jobId, Long userId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Application application = new Application();
        application.setJob(job);
        application.setApplicant(user);
        application.setStatus(ApplicationStatus.APPLIED);

        return applicationRepository.save(application);
    }

    @Override
    public List<Application> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    @Override
    public List<Application> getApplicationsByUser(Long userId) {
        return applicationRepository.findByApplicantId(userId);
    }

    @Override
    public Application updateApplicationStatus(Long applicationId, ApplicationStatus status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setStatus(status);
        return applicationRepository.save(application);
    }
}
