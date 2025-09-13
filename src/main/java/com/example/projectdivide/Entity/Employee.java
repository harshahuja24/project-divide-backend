package com.example.projectdivide.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eid;

    private String ename;
    private boolean isAdmin;
    private String password;
    private String email;

    // FIXED: Changed from @OneToOne to @ManyToOne
    // Many employees can belong to one sprint
    @ManyToOne
    @JoinColumn(name = "sprint_id") // This creates a foreign key column
    private Sprint sprint;

    @OneToMany(mappedBy = "employee")
    private List<Task> taskList;
}