package com.jobmatch.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobmatch.backend.Job;
import com.jobmatch.backend.Resume;
import com.jobmatch.backend.repository.JobRepository;
import com.jobmatch.backend.repository.ResumeRepository;

@Service
public class MLService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private JobRepository jobRepository;

    // send resume to ML
    public String sendResumeToML(String email){

        List<Resume> resumes = resumeRepository.findByEmail(email);

        if(resumes.isEmpty()){
            throw new RuntimeException("No resume found for this email");
        }

        Resume latestResume = resumes.get(resumes.size()-1);

        // later call python API here
        return "Resume sent to ML model for email: " + email +
               " | File: " + latestResume.getFileName();
    }


    // match resume with job
    public String matchResumeWithJob(String email, Long jobId){

        List<Resume> resumes = resumeRepository.findByEmail(email);

        if(resumes.isEmpty()){
            throw new RuntimeException("Resume not found");
        }

        Resume resume = resumes.get(resumes.size()-1);

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // later ML integration here
        return "Matching done between resume and job → " +
                job.getTitle() + " | Resume: " + resume.getFileName();
    }
}