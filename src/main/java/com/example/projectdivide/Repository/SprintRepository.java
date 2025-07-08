package com.example.projectdivide.Repository;

import com.example.projectdivide.Entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SprintRepository extends JpaRepository<Sprint, Integer> {
    
    public Optional<Sprint> findBySprintId(int sprintId);
    // In SprintRepository
    Optional<Sprint> findByActiveYNTrue();

}
