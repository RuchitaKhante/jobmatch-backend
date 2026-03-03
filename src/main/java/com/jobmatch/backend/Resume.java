package com.jobmatch.backend;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String filePath;
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
