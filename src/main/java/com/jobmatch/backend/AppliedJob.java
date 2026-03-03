package com.jobmatch.backend;

import jakarta.persistence.*;

@Entity
@Table(name="applied_jobs")
public class AppliedJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private Long jobId;

    // getters setters
    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public String getUserEmail(){ return userEmail; }
    public void setUserEmail(String userEmail){ this.userEmail=userEmail; }

    public Long getJobId(){ return jobId; }
    public void setJobId(Long jobId){ this.jobId=jobId; }
}