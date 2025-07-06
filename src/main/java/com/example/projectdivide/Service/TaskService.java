package com.example.projectdivide.Service;

import com.example.projectdivide.DTO.TaskDTO;
import com.example.projectdivide.Entity.Task;
import com.example.projectdivide.Repository.TaskRepository;
import com.example.projectdivide.mapper.TaskDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskDTOMapper taskDTOMapper;

    @Autowired
    TaskRepository taskRepository;

    public void createTask(TaskDTO taskDTO) {
        Task task = taskDTOMapper.toEntity(taskDTO);
        taskRepository.save(task);
    }

    public List<TaskDTO> getAllTasks() {
        List<Task> taskList = taskRepository.findAll();
        return taskList.stream()
                .map((t) -> taskDTOMapper.toDTO(t))
                .toList();
    }

    public TaskDTO getTaskById(int id) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()) {
            TaskDTO taskDTO = taskDTOMapper.toDTO(task.get());
            return taskDTO;
        }
        throw new RuntimeException("Run time error in Task service - Task not found with id: " + id);
    }

    // Additional methods you might need:

//    public List<TaskDTO> getTasksByEmployee(int employeeId) {
//        List<Task> taskList = taskRepository.findByEmployee_Eid(employeeId);
//        return taskList.stream()
//                .map((t) -> taskDTOMapper.toDTO(t))
//                .toList();
//    }
//
//    public List<TaskDTO> getTasksBySprint(int sprintId) {
//        List<Task> taskList = taskRepository.findBySprint_SprintId(sprintId);
//        return taskList.stream()
//                .map((t) -> taskDTOMapper.toDTO(t))
//                .toList();
//    }

    public void updateTask(TaskDTO taskDTO) {
        Task task = taskDTOMapper.toEntity(taskDTO);
        taskRepository.save(task);
    }

    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }
}
