package com.jobmatch.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jobmatch.backend.Job;
import com.jobmatch.backend.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // add job
    @PostMapping("/add")
    public Job addJob(@RequestBody Job job){
        return jobService.addJob(job);
    }

    // get all jobs
    @GetMapping("/all")
    public List<Job> getAllJobs(){
        return jobService.getAllJobs();
    }

    // get job by id
    @GetMapping("/{id}")
    public Job getJob(@PathVariable Long id){
        return jobService.getJobById(id);
    }

    // delete job
    @DeleteMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id){
        return jobService.deleteJob(id);
    }
    
    @GetMapping("/location")
    public List<Job> jobsByLocation(@RequestParam String location){
        return jobService.getJobsByLocation(location);
    }

    @GetMapping("/search")
    public List<Job> searchJobs(@RequestParam String keyword){
        return jobService.searchJobs(keyword);
    }
    
 // update job
    @PutMapping("/update/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job job){
        return jobService.updateJob(id, job);
    }
}