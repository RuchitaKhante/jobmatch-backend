package com.jobmatch.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jobmatch.backend.service.MLService;

@RestController
@RequestMapping("/ml")
public class MLController {

    @Autowired
    private MLService mlService;

    // send resume to ML model
    @PostMapping("/send")
    public String sendResume(@RequestParam String email){
        return mlService.sendResumeToML(email);
    }


    // match resume + job
    @PostMapping("/match")
    public String matchResumeWithJob(@RequestParam String email,
                                     @RequestParam Long jobId){

        return mlService.matchResumeWithJob(email, jobId);
    }
}