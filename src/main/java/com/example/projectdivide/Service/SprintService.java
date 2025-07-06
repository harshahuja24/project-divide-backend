package com.example.projectdivide.Service;

import com.example.projectdivide.DTO.SprintDTO;
import com.example.projectdivide.Entity.Sprint;
import com.example.projectdivide.Repository.SprintRepository;
import com.example.projectdivide.mapper.SprintDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SprintService {

    @Autowired
    private SprintDTOMapper sprintDTOMapper;

    @Autowired
    private SprintRepository sprintRepository;

    // Create new sprint
    public void createSprint(SprintDTO sprintDTO) {
        Sprint sprint = sprintDTOMapper.toEntity(sprintDTO);
        sprintRepository.save(sprint);
    }

    // Get all sprints
    public List<SprintDTO> getAllSprints() {
        List<Sprint> sprintList = sprintRepository.findAll();
        return sprintList.stream()
                .map(sprint -> sprintDTOMapper.toDTO(sprint))
                .toList();
    }

    // Get sprint by ID
    public SprintDTO getSprintById(int id) {
        Optional<Sprint> sprint = sprintRepository.findBySprintId(id);
        if (sprint.isPresent()) {
            return sprintDTOMapper.toDTO(sprint.get());
        }
        throw new RuntimeException("Sprint not found with ID: " + id);
    }

    // Get active sprint (current date between start and end date)
    public SprintDTO getActiveSprint() {
        LocalDateTime now = LocalDateTime.now();
        List<Sprint> allSprints = sprintRepository.findAll();

        for (Sprint sprint : allSprints) {
            if (sprint.getStartDate() != null && sprint.getEndDate() != null) {
                if (now.isAfter(sprint.getStartDate()) && now.isBefore(sprint.getEndDate())) {
                    return sprintDTOMapper.toDTO(sprint);
                }
            }
        }

        // If no active sprint found, return null
        return null;
    }

    // Update sprint
    public void updateSprint(SprintDTO sprintDTO) {
        Optional<Sprint> existingSprint = sprintRepository.findBySprintId(sprintDTO.getSprintId());
        if (existingSprint.isPresent()) {
            Sprint sprint = existingSprint.get();
            sprint.setSprintTitle(sprintDTO.getSprintTitle());
            sprint.setSprintDesc(sprintDTO.getSprintDesc());
            sprint.setStartDate(sprintDTO.getStartDate());
            sprint.setEndDate(sprintDTO.getEndDate());
            sprintRepository.save(sprint);
        } else {
            throw new RuntimeException("Sprint not found with ID: " + sprintDTO.getSprintId());
        }
    }

    // Delete sprint
    public void deleteSprint(int id) {
        Optional<Sprint> sprint = sprintRepository.findBySprintId(id);
        if (sprint.isPresent()) {
            sprintRepository.delete(sprint.get());
        } else {
            throw new RuntimeException("Sprint not found with ID: " + id);
        }
    }
}