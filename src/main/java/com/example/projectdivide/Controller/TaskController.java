package com.example.projectdivide.Controller;

import com.example.projectdivide.DTO.SprintDTO;
import com.example.projectdivide.DTO.TaskDTO;
import com.example.projectdivide.Service.SprintService;
import com.example.projectdivide.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    SprintService sprintService;

    // Create a new task
    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody TaskDTO taskDTO) {
        try {
            taskService.createTask(taskDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating task: " + e.getMessage());
        }
    }

    // Get all tasks
    @GetMapping("/all")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        try {
            List<TaskDTO> tasks = taskService.getAllTasks();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable int id) {
        try {
            TaskDTO task = taskService.getTaskById(id);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    // Get tasks by employee ID
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<TaskDTO>> getTasksByEmployee(@PathVariable int employeeId) {
        try {
            List<TaskDTO> tasks = taskService.getTasksByEmployee(employeeId);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // Get tasks by sprint ID
    @GetMapping("/bySprintId/{sprintId}")
    public ResponseEntity<List<TaskDTO>> getTasksBySprint(@PathVariable int sprintId) {
        try {
            List<TaskDTO> tasks = taskService.getTasksBySprint(sprintId);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // Update task
    @PutMapping("/update")
    public ResponseEntity<String> updateTask(@RequestBody TaskDTO taskDTO) {
        try {
            taskService.updateTask(taskDTO);
            return ResponseEntity.ok("Task updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating task: " + e.getMessage());
        }
    }

//    // Update task by ID
//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updateTaskById(@PathVariable int id, @RequestBody TaskDTO taskDTO) {
//        try {
//            taskDTO.setTaskId(id); // Ensure the ID matches
//            taskService.updateTask(taskDTO);
//            return ResponseEntity.ok("Task updated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Error updating task: " + e.getMessage());
//        }
//    }

    // Delete task
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok("Task deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error deleting task: " + e.getMessage());
        }
    }

//    // Get tasks by status
//    @GetMapping("/status/{status}")
//    public ResponseEntity<List<TaskDTO>> getTasksByStatus(@PathVariable String status) {
//        try {
//            List<TaskDTO> tasks = taskService.getTasksByStatus(status);
//            return ResponseEntity.ok(tasks);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
//    }
//
//    // Get active tasks
//    @GetMapping("/active")
//    public ResponseEntity<List<TaskDTO>> getActiveTasks() {
//        try {
//            List<TaskDTO> tasks = taskService.getActiveTasks();
//            return ResponseEntity.ok(tasks);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
//    }

    @GetMapping("/active-sprint")
    public ResponseEntity<List<TaskDTO>> getTasksOfActiveSprint() {
        try {
            SprintDTO activeSprint = sprintService.getActiveSprint();
            if (activeSprint == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            int sprintId = activeSprint.getSprintId();
            List<TaskDTO> tasks = taskService.getTasksBySprint(sprintId);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/active-sprint/employee/{employeeId}")
    public ResponseEntity<List<TaskDTO>> getActiveSprintTasksByEmployee(@PathVariable int employeeId) {
        try {
            List<TaskDTO> tasks = taskService.getActiveSprintTasksByEmployee(employeeId);
            if (tasks == null || tasks.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/updateStatus")
    public void updateStatus(@RequestBody TaskDTO body){
        System.out.println("Hello from update stataus");
        System.out.println(body);
        taskService.updateStatus(body.getTaskId(), body.getTaskStatus());
    }
}

