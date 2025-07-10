package com.example.projectdivide.Repository;

import com.example.projectdivide.Entity.Sprint;
import com.example.projectdivide.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Integer> {

    List<Task> findBySprint_SprintId(int sprintId);
    List<Task> findByAssignedTo(int assignedTo);
    Optional<Task> findByTaskId(int id);

}
