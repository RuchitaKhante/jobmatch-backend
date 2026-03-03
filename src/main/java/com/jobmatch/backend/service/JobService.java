package com.jobmatch.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobmatch.backend.Job;
import com.jobmatch.backend.repository.JobRepository;
import org.slf4j.Logger;
import com.jobmatch.backend.util.LoggerUtil;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    private static final Logger logger = LoggerUtil.getLogger(JobService.class);


    // add job
    public Job addJob(Job job){
        logger.info("Adding new job: {}", job.getTitle());
        return jobRepository.save(job);
    }

    // get all jobs
    public List<Job> getAllJobs(){
        logger.info("Fetching all jobs");
        return jobRepository.findAll();
    }

    // get job by id
    public Job getJobById(Long id){
        logger.info("Fetching job with id: {}", id);

        return jobRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Job not found with id: {}", id);
                    return new RuntimeException("Job not found");
                });
    }

    // delete job
    public String deleteJob(Long id){
        logger.info("Deleting job with id: {}", id);
        jobRepository.deleteById(id);
        return "Job deleted successfully";
    }

    // update job
    public Job updateJob(Long id, Job updatedJob){

        logger.info("Updating job with id: {}", id);

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Update failed. Job not found id: {}", id);
                    return new RuntimeException("Job not found");
                });

        job.setTitle(updatedJob.getTitle());
        job.setCompany(updatedJob.getCompany());
        job.setLocation(updatedJob.getLocation());
        job.setDescription(updatedJob.getDescription());

        return jobRepository.save(job);
    }

    // filter by location
    public List<Job> getJobsByLocation(String location){
        logger.info("Fetching jobs in location: {}", location);
        return jobRepository.findByLocation(location);
    }

    // search job
    public List<Job> searchJobs(String keyword){
        logger.info("Searching jobs with keyword: {}", keyword);
        return jobRepository.findByTitleContainingIgnoreCase(keyword);
    }
}