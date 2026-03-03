package com.jobmatch.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jobmatch.backend.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    List<Resume> findByEmail(String email);

}