package com.example.projectdivide.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sprintId;

    private String sprintTitle;
    private String sprintDesc;
    @CreationTimestamp
    private LocalDateTime startDate;
    @CreationTimestamp
    private LocalDateTime endDate;

    @OneToOne(mappedBy = "sprint")
    private Employee employee;

    @OneToMany(mappedBy = "sprint")
    private List<Task> taskList;
}
