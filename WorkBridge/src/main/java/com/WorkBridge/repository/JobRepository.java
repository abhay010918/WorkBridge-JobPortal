package com.WorkBridge.repository;

import com.WorkBridge.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByCategory(String category);

    List<Job> findByCompany(String company);

    List<Job> findByTitleContainingIgnoreCase(String title);

    List<Job> findByRecruiterId(Long recruiterId);
}