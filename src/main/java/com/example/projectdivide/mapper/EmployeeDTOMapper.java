package com.example.projectdivide.mapper;

import com.example.projectdivide.DTO.EmployeeDTO;
import com.example.projectdivide.Entity.Employee;
import com.example.projectdivide.Entity.Sprint;
import com.example.projectdivide.Repository.EmployeeRepository;
import com.example.projectdivide.Repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class EmployeeDTOMapper {


    @Autowired
    SprintRepository sprintRepository;

    EmployeeDTO toDTO(Employee employee){

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEid(employee.getEid());
        employeeDTO.setEname(employee.getEname());
        employeeDTO.setAdmin(employee.isAdmin());
        return employeeDTO;
    }


    Employee toEntity(EmployeeDTO employeeDTO){

        Employee employee = new Employee();

        Optional<Sprint> sprint = sprintRepository.findBySprintId(employeeDTO.getCurrentSprintId());

        if (sprint.isPresent()) {
            employee.setEid(employeeDTO.getEid());
            employee.setEname(employeeDTO.getEname());
            employee.setAdmin(employeeDTO.isAdmin());
            employee.setSprint(sprint.get());

            return employee;


        }
        throw new RuntimeException("Sprint invalid");

    }
}
