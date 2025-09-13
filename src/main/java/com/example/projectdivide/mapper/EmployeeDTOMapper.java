package com.example.projectdivide.mapper;

import com.example.projectdivide.DTO.EmployeeDTO;
import com.example.projectdivide.Entity.Employee;
import com.example.projectdivide.Entity.Sprint;
import com.example.projectdivide.Repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class EmployeeDTOMapper {

    @Autowired
    SprintRepository sprintRepository;

    public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEid(employee.getEid());
        employeeDTO.setEname(employee.getEname());
        employeeDTO.setAdmin(employee.isAdmin());
        employeeDTO.setEmail(employee.getEmail());


        // FIXED: Set currentSprintId from the sprint relationship
        if (employee.getSprint() != null) {
            employeeDTO.setCurrentSprintId(employee.getSprint().getSprintId());
        } else {
            employeeDTO.setCurrentSprintId(0); // or -1 to indicate no sprint assigned
        }

        return employeeDTO;
    }

    public Employee toEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        // For new employee creation, don't set the ID
        if (employeeDTO.getEid() != 0) {
            employee.setEid(employeeDTO.getEid());
        }

        employee.setEname(employeeDTO.getEname());
        employee.setAdmin(employeeDTO.isAdmin());
        employee.setEmail(employeeDTO.getEmail());


        // FIXED: Handle sprint assignment properly
        if (employeeDTO.getCurrentSprintId() != 0) {
            Optional<Sprint> sprint = sprintRepository.findBySprintId(employeeDTO.getCurrentSprintId());
            if (sprint.isPresent()) {
                employee.setSprint(sprint.get());
            } else {
                throw new RuntimeException("Sprint not found with ID: " + employeeDTO.getCurrentSprintId());
            }
        }
        // If currentSprintId is 0, employee has no sprint assigned (sprint remains null)

        return employee;
    }
}