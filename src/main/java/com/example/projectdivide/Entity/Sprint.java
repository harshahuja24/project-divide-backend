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

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    // FIXED: Changed from @OneToOne to @OneToMany
    // One sprint can have many employees
    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy = "sprint")
    private List<Task> taskList;
}