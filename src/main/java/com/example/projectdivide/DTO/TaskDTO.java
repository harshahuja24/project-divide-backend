package com.example.projectdivide.DTO;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
//@AllArgsConstructor
@ToString
public class TaskDTO {
    private int taskId;
    private int assignedTo;
    private int assignedFrom;
    private int sprintId;
    private String taskTitle;
    private String taskDesc;
    private String taskStatus;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private boolean activeYN;
    private LocalDateTime completedAt;
//    private int sprintId;
}
