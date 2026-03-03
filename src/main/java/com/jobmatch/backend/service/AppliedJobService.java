package com.jobmatch.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobmatch.backend.AppliedJob;
import com.jobmatch.backend.repository.AppliedJobRepository;

@Service
public class AppliedJobService {

    @Autowired
    private AppliedJobRepository repo;

    // apply job
    public AppliedJob apply(String email, Long jobId){

        if(repo.existsByUserEmailAndJobId(email, jobId)){
            throw new RuntimeException("Already applied");
        }

        AppliedJob job = new AppliedJob();
        job.setUserEmail(email);
        job.setJobId(jobId);

        return repo.save(job);
    }

    // get applied jobs
    public List<AppliedJob> getApplied(String email){
        return repo.findByUserEmail(email);
    }
}