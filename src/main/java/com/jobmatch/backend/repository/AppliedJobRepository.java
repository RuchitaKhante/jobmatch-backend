package com.jobmatch.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jobmatch.backend.AppliedJob;

public interface AppliedJobRepository extends JpaRepository<AppliedJob, Long> {

    List<AppliedJob> findByUserEmail(String email);

    boolean existsByUserEmailAndJobId(String email, Long jobId);
}