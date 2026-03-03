package com.jobmatch.backend.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jobmatch.backend.Resume;
import com.jobmatch.backend.User;
import com.jobmatch.backend.repository.ResumeRepository;
import com.jobmatch.backend.repository.UserRepository;

@Service
public class ResumeService {

    private final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private UserRepository userRepository;


    // Upload Resume
    public Resume uploadResume(String email, MultipartFile file) throws IOException {

        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) dir.mkdirs();

        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Resume resume = new Resume();
        resume.setEmail(email);
        resume.setFileName(file.getOriginalFilename());
        resume.setFilePath(filePath);
        resume.setUser(user);

        return resumeRepository.save(resume);
    }


    // Get all resumes of user
    public List<Resume> getResumesByEmail(String email){

        List<Resume> resumes = resumeRepository.findByEmail(email);

        if(resumes.isEmpty()){
            throw new RuntimeException("No resumes found for this email");
        }

        return resumes;
    }


    // Get latest uploaded resume file
    public File getLatestResumeFile(String email){

        List<Resume> resumes = resumeRepository.findByEmail(email);

        if(resumes.isEmpty()){
            throw new RuntimeException("Resume not found");
        }

        Resume latestResume = resumes.get(resumes.size() - 1);

        return new File(latestResume.getFilePath());
    }
    
 // Delete resume by id
    public String deleteResume(Long id){

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        // delete file from folder
        File file = new File(resume.getFilePath());
        if(file.exists()){
            file.delete();
        }

        // delete from DB
        resumeRepository.delete(resume);

        return "Resume deleted successfully";
    }
    
    public Resume saveResume(Resume resume){
        return resumeRepository.save(resume);
    }
}