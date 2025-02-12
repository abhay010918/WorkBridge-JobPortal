package com.WorkBridge.service;

import com.WorkBridge.entity.AppUser;
import com.WorkBridge.entity.Job;
import com.WorkBridge.repository.AppUserRepository;
import com.WorkBridge.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final AppUserRepository userRepository;

    public JobService(JobRepository jobRepository, AppUserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public Job postJob(Job job, Long recruiterId) {
        AppUser recruiter = userRepository.findById(recruiterId)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        job.setRecruiter(recruiter); // 🔹 Set recruiter before saving
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public List<Job> getJobsByCategory(String category) {
        return jobRepository.findByCategory(category);
    }

    public List<Job> getJobsByCompany(String company) {
        return jobRepository.findByCompany(company);
    }

    public List<Job> searchJobs(String title) {
        return jobRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Job> getJobsByRecruiter(Long recruiterId) {
        return jobRepository.findByRecruiterId(recruiterId);
    }

    public Job updateJob(Long id, Job updatedJob) {
        return jobRepository.findById(id).map(job -> {
            job.setTitle(updatedJob.getTitle());
            job.setCompany(updatedJob.getCompany());
            job.setLocation(updatedJob.getLocation());
            job.setDescription(updatedJob.getDescription());
            job.setCategory(updatedJob.getCategory());
            job.setSalary(updatedJob.getSalary());
            return jobRepository.save(job);
        }).orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
