package com.WorkBridge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category; // IT, Finance, Healthcare, etc.

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private AppUser employer; // The employer who posted the job

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private AppUser recruiter; // The employer posting the job

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // 🔹 Prevent infinite loop
    private List<Application> applications;

    @Column(nullable = false)
    private LocalDateTime postedAt = LocalDateTime.now();

}
