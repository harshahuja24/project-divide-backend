package com.example.projectdivide.mapper;

import com.example.projectdivide.DTO.SprintDTO;
import com.example.projectdivide.Entity.Sprint;
import com.example.projectdivide.Repository.EmployeeRepository;
import com.example.projectdivide.Repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SprintDTOMapper {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SprintRepository sprintRepository;

    // Convert Sprint entity to SprintDTO
    public SprintDTO toDTO(Sprint sprint) {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setSprintId(sprint.getSprintId());
        sprintDTO.setSprintTitle(sprint.getSprintTitle());
        sprintDTO.setSprintDesc(sprint.getSprintDesc());
        sprintDTO.setStartDate(sprint.getStartDate());
        sprintDTO.setEndDate(sprint.getEndDate());
        return sprintDTO;
    }

    // Convert SprintDTO to Sprint entity
    public Sprint toEntity(SprintDTO sprintDTO) {
        Sprint sprint = new Sprint();

        // For new sprint creation (sprintId is 0 or not set)
        if (sprintDTO.getSprintId() == 0) {
            sprint.setSprintTitle(sprintDTO.getSprintTitle());
            sprint.setSprintDesc(sprintDTO.getSprintDesc());
            sprint.setStartDate(sprintDTO.getStartDate());
            sprint.setEndDate(sprintDTO.getEndDate());
            return sprint;
        }

        // For existing sprint update
        Optional<Sprint> existingSprint = sprintRepository.findBySprintId(sprintDTO.getSprintId());
        if (existingSprint.isPresent()) {
            sprint.setSprintId(sprintDTO.getSprintId());
            sprint.setSprintTitle(sprintDTO.getSprintTitle());
            sprint.setSprintDesc(sprintDTO.getSprintDesc());
            sprint.setStartDate(sprintDTO.getStartDate());
            sprint.setEndDate(sprintDTO.getEndDate());

            // FIXED: Changed from setEmployee() to setEmployees()
            // because Sprint now has List<Employee> employees instead of Employee employee
            sprint.setEmployees(existingSprint.get().getEmployees());
            sprint.setTaskList(existingSprint.get().getTaskList());
            return sprint;
        } else {
            // If sprint ID is provided but doesn't exist, create new sprint
            throw new RuntimeException("Sprint not found with the provided ID");
        }
    }
}