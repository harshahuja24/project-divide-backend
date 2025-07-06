package com.example.projectdivide.Controller;

import com.example.projectdivide.DTO.EmployeeDTO;
import com.example.projectdivide.Entity.Employee;
import com.example.projectdivide.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Create new employee
    @PostMapping("/createEmployee")
    public void createEmployee(@RequestBody EmployeeDTO employeeDTO) {

            employeeService.createEmployee(employeeDTO);

    }

    // Get all employees
    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        try {
            List<EmployeeDTO> employees = employeeService.getAllEmployees();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id) {
        try {
            EmployeeDTO employee = employeeService.getEmployeeById(id);
            if (employee != null) {
                return new ResponseEntity<>(employee, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}