package com.example.projectdivide.Controller;

import com.example.projectdivide.DTO.SprintDTO;
import com.example.projectdivide.Service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sprint")
@CrossOrigin(origins = "http://localhost:4200")
public class SprintController {

    @Autowired
    private SprintService sprintService;

    // Create new sprint
    @PostMapping("/createSprint")
    public ResponseEntity<String> createSprint(@RequestBody SprintDTO sprintDTO) {
        try {
            sprintService.createSprint(sprintDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating sprint: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all sprints
    @GetMapping("/getAllSprints")
    public ResponseEntity<List<SprintDTO>> getAllSprints() {
        try {
            List<SprintDTO> sprints = sprintService.getAllSprints();
            return new ResponseEntity<>(sprints, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get sprint by ID
    @GetMapping("/{id}")
    public ResponseEntity<SprintDTO> getSprintById(@PathVariable int id) {
        try {
            SprintDTO sprint = sprintService.getSprintById(id);
            if (sprint != null) {
                return new ResponseEntity<>(sprint, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get active sprint (assuming current date is between start and end date)
    @GetMapping("/getActiveSprint")
    public ResponseEntity<SprintDTO> getActiveSprint() {
        try {
            SprintDTO activeSprint = sprintService.getActiveSprint();
            if (activeSprint != null) {
                return new ResponseEntity<>(activeSprint, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update sprint
    @PutMapping("/updateSprint/{id}")
    public ResponseEntity<String> updateSprint(@PathVariable int id, @RequestBody SprintDTO sprintDTO) {
        try {
            sprintDTO.setSprintId(id);
            sprintService.updateSprint(sprintDTO);
            return new ResponseEntity<>("Sprint updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating sprint: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete sprint
    @DeleteMapping("/deleteSprint/{id}")
    public ResponseEntity<String> deleteSprint(@PathVariable int id) {
        try {
            sprintService.deleteSprint(id);
            return new ResponseEntity<>("Sprint deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting sprint: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}