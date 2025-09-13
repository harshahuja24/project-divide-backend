package com.example.projectdivide.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;
    private int assignedTo;
    private int assignedFrom;
    private String taskTitle;
    private String taskDesc;
    private String taskStatus;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private boolean activeYN;
    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    // 1 task can be assigned to 1 employee
//    @OneToOne
//    @JoinColumn(name = "eid", referencedColumnName = "eid")
//    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
