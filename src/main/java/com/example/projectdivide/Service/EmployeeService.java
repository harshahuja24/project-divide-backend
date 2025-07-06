package com.example.projectdivide.Service;

import com.example.projectdivide.DTO.EmployeeDTO;
import com.example.projectdivide.Entity.Employee;
import com.example.projectdivide.Repository.EmployeeRepository;
import com.example.projectdivide.mapper.EmployeeDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDTOMapper employeeDTOMapper;

    @Autowired
    EmployeeRepository employeeRepository;


    public void createEmployee(EmployeeDTO employeeDTO) {

            Employee employee = employeeDTOMapper.toEntity(employeeDTO);
            employeeRepository.save(employee);
//            return employeeRepository.findByEid(employeeDTO.getEid());

    }

    public List<EmployeeDTO> getAllEmployees() {

            List<Employee> employeeList = employeeRepository.findAll();
            return employeeList.stream()
                    .map((e)->employeeDTOMapper.toDTO(e))
                    .toList();

    }

    public EmployeeDTO getEmployeeById(int id) {

        Optional<Employee> employee = employeeRepository.findByEid(id);

        if(employee.isPresent()){

            EmployeeDTO employeeDTO = employeeDTOMapper.toDTO(employee.get());
            return employeeDTO;

        }

        throw new RuntimeException("Run time error in Employee service ");



    }


}
