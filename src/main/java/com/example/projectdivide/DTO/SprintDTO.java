package com.example.projectdivide.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
//@AllArgsConstructor
@ToString

public class SprintDTO {
    private int sprintId;
    private String sprintTitle;
    private String sprintDesc;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
