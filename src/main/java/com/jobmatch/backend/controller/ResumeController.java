package com.jobmatch.backend.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jobmatch.backend.Resume;
import com.jobmatch.backend.service.ResumeService;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;


    // Upload Resume
    @PostMapping("/upload")
    public String uploadResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("email") String email) throws IOException {

        System.out.println("DEBUG FILE = " + file);
        System.out.println("Filename = " + file.getOriginalFilename());

        String uploadDir = System.getProperty("user.dir") + "/uploads/";
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = uploadDir + file.getOriginalFilename();
        File dest = new File(filePath);
        file.transferTo(dest);

        Resume resume = new Resume();
        resume.setEmail(email);
        resume.setFileName(file.getOriginalFilename());
        resume.setFilePath(dest.getAbsolutePath());

        resumeService.saveResume(resume);   // ← use service not repository

        return "Resume uploaded successfully!";
    }
    
    // Get all resumes by email (manual input)
    @GetMapping("/all")
    public List<Resume> getAllResumes(@RequestParam String email){
        return resumeService.getResumesByEmail(email);
    }


    // Get resumes of logged-in user
    @GetMapping("/my")
    public List<Resume> getMyResumes(Principal principal){
        return resumeService.getResumesByEmail(principal.getName());
    }


    // Download latest resume of logged-in user
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadLatest(Principal principal){

        File file = resumeService.getLatestResumeFile(principal.getName());

        Resource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteResume(@PathVariable Long id){
        return resumeService.deleteResume(id);
    }
}