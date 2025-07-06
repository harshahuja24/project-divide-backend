package com.example.projectdivide.DTO;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@AllArgsConstructor
@ToString
public class EmployeeDTO {
    private int eid;
    private String ename;
    private boolean isAdmin = false;
    private int currentSprintId;
}
