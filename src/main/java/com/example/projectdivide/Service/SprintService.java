package com.example.projectdivide.Service;

import com.example.projectdivide.DTO.SprintDTO;
import com.example.projectdivide.Entity.Sprint;
import com.example.projectdivide.Entity.Task;
import com.example.projectdivide.Repository.SprintRepository;
import com.example.projectdivide.Repository.TaskRepository;
import com.example.projectdivide.mapper.SprintDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SprintService {

    @Autowired
    private SprintDTOMapper sprintDTOMapper;

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private TaskRepository taskRepository;


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
//    public SprintDTO getActiveSprint() {
//        LocalDateTime now = LocalDateTime.now();
//        List<Sprint> allSprints = sprintRepository.findAll();
//
//        for (Sprint sprint : allSprints) {
//            if (sprint.getStartDate() != null && sprint.getEndDate() != null) {
//                if (now.isAfter(sprint.getStartDate()) && now.isBefore(sprint.getEndDate())) {
//                    return sprintDTOMapper.toDTO(sprint);
//                }
//            }
//        }
//
//        // If no active sprint found, return null
//        return null;
//    }

    public SprintDTO getActiveSprint() {
//        Optional<Sprint> sprint = sprintRepository.findByActiveYNTrue();
//        System.out.println(sprint.get().toString());
//        SprintDTO sprintDTO = sprintDTOMapper.toDTO(sprint.get());
//        return sprintDTO;

        List<SprintDTO> sprints = getAllSprints();
        for(SprintDTO sprint: sprints){
            if(sprint.isActiveYN()){
                return sprint;
            }
        }

        return null;
    }


    // Update sprint
    public void updateSprint(SprintDTO sprintDTO) {
        Optional<Sprint> existingSprint = sprintRepository.findBySprintId(sprintDTO.getSprintId());
        if (existingSprint.isPresent()) {
            Sprint sprint = existingSprint.get();
            sprint.setSprintTitle(sprintDTO.getSprintTitle());
            sprint.setSprintDesc(sprintDTO.getSprintDesc());
            sprint.setStartDate(sprintDTO.getStartDate().atStartOfDay());
            sprint.setEndDate(sprintDTO.getEndDate().atStartOfDay());
            sprintRepository.save(sprint);
        } else {
            throw new RuntimeException("Sprint not found with ID: " + sprintDTO.getSprintId());
        }
    }

    public void startSprint(int id){
        Optional<Sprint> existingSprint = sprintRepository.findBySprintId(id);
        if(existingSprint.isPresent()){
            Sprint s = existingSprint.get();
            List<Task> tasks = taskRepository.findBySprint_SprintId(s.getSprintId());
            for(Task task : tasks){
                updateStatus(task.getTaskId(), "TODO");

            }
            s.setActiveYN(true);
            sprintRepository.save(s);
        }
        else{
            throw new RuntimeException("SPrint nahi mili bebu");
        }
    }

    public void stopSprint(int id){
        Optional<Sprint> existingSprint = sprintRepository.findBySprintId(id);
        if(existingSprint.isPresent()){
            Sprint s = existingSprint.get();
            s.setActiveYN(false);
            sprintRepository.save(s);
        }
        else{
            throw new RuntimeException("SPrint nahi mili bebu");
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

    public void updateStatus(int id, String status){
        Optional<Task> task = taskRepository.findByTaskId(id);

        if(task.isPresent()){

            Task t = task.get();
            System.out.println("LOL "+t);
            t.setTaskStatus(status);
            System.out.println(t);

            if(Objects.equals(status, "TODO"))   t.setStartedAt(LocalDateTime.now());
            if(Objects.equals(status, "DONE")) t.setCompletedAt(LocalDateTime.now());
            taskRepository.save(t);
        }else{
            throw new RuntimeException(" BEbu task nahi mila hai ");
        }
    }
}