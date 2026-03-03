package com.jobmatch.backend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jobmatch.backend.AppliedJob;
import com.jobmatch.backend.service.AppliedJobService;

@RestController
@RequestMapping("/apply")
public class AppliedJobController {

    @Autowired
    private AppliedJobService service;

    // apply job
    @PostMapping("/{jobId}")
    public AppliedJob applyJob(@PathVariable Long jobId, Principal principal){

        String email = principal.getName();
        return service.apply(email, jobId);
    }

    // get applied jobs
    @GetMapping("/my")
    public List<AppliedJob> myApplications(Principal principal){
        return service.getApplied(principal.getName());
    }
}