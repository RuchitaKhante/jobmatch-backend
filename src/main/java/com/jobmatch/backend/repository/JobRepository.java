package com.jobmatch.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobmatch.backend.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
	
	List<Job> findByLocation(String location);

	List<Job> findByTitleContainingIgnoreCase(String keyword);
	
}