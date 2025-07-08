package com.example.projectdivide.Repository;

import com.example.projectdivide.DTO.EmployeeDTO;
import com.example.projectdivide.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

//    public Sprint findSprintByEmployeeId(int employeeId ){
//
//
//    }

    Optional<Employee> findByEid(int eid);


    @Modifying
    @Transactional
    @Query(value = "UPDATE employee SET sprint_id = :sprintId WHERE eid IN (:employeeIds)", nativeQuery = true)
    void updateSprintIdForEmployees(@Param("sprintId") Long sprintId, @Param("employeeIds") List<Long> employeeIds);

}
