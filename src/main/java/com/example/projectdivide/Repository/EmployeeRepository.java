package com.example.projectdivide.Repository;

import com.example.projectdivide.Entity.Employee;
import com.example.projectdivide.Entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

//    public Sprint findSprintByEmployeeId(int employeeId ){
//
//
//    }

    Optional<Employee> findByEid(int eid);



}
