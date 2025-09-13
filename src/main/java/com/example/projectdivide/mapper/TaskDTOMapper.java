package com.example.projectdivide.mapper;

import com.example.projectdivide.DTO.TaskDTO;
import com.example.projectdivide.Entity.Employee;
import com.example.projectdivide.Entity.Sprint;
import com.example.projectdivide.Entity.Task;
import com.example.projectdivide.Repository.EmployeeRepository;
import com.example.projectdivide.Repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class TaskDTOMapper {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SprintRepository sprintRepository;


    public Task toEntity(TaskDTO taskDTO) {

        Task task = new Task();

        Optional<Employee> employee = employeeRepository.findByEid(taskDTO.getAssignedTo());

        Optional<Sprint> sprint = sprintRepository.findBySprintId(taskDTO.getSprintId());

        if (employee.isPresent() && sprint.isPresent()) {
            task.setTaskId(taskDTO.getTaskId());
            task.setTaskTitle(taskDTO.getTaskTitle());
            task.setActiveYN(taskDTO.isActiveYN());
            task.setTaskDesc(taskDTO.getTaskDesc());
            task.setEmployee(employee.get());
            task.setTaskStatus(taskDTO.getTaskStatus());
            task.setAssignedTo(taskDTO.getAssignedTo());
            task.setAssignedFrom(task.getAssignedFrom());
            task.setSprint(sprint.get());
            task.setCreatedAt(taskDTO.getCreatedAt());
            task.setStartedAt(taskDTO.getStartedAt());
            task.setCompletedAt(taskDTO.getCompletedAt());

            return task;
        } else {

            throw new RuntimeException("Nahi mila re tera Employee / Sprint ");
        }

    }


    public TaskDTO toDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskId(task.getTaskId());
        taskDTO.setTaskTitle(task.getTaskTitle());
        taskDTO.setTaskDesc(task.getTaskDesc());
        taskDTO.setTaskStatus(task.getTaskStatus());
        taskDTO.setActiveYN(task.isActiveYN());
        taskDTO.setAssignedTo(task.getAssignedTo());
        taskDTO.setAssignedFrom(task.getAssignedFrom());
        taskDTO.setCreatedAt(task.getCreatedAt());
        taskDTO.setStartedAt(task.getStartedAt());
        taskDTO.setCompletedAt(task.getCompletedAt());

        // Set sprintId from the Sprint entity
        if (task.getSprint() != null) {
            taskDTO.setSprintId(task.getSprint().getSprintId());
        }

        // Note: assignedTo is already set from the primitive field
        // If you also want to get it from the Employee entity relationship:
        // if (task.getEmployee() != null) {
        //     taskDTO.setAssignedTo(task.getEmployee().getEid());
        // }

        return taskDTO;
    }


}



