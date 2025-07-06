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
    private boolean isAdmin = false;

    @OneToOne
    @JoinColumn(name = "sprint_id") // This creates a foreign key column
    private Sprint sprint;

    @OneToMany(mappedBy = "employee")
     private List<Task> taskList;

//    1 admin can create  M sprints
//1 admin can create M employees
//1 sprint can have M tasks
//1 task can be assined to 1 employee  koi bhi shared task nahi hoga
//1 admin can create M task
//1 employee can have 1 sprint
    //1 employee can have M task    admin ek admi ko 5 task de sakta hai
}
