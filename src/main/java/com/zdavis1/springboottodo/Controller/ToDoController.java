package com.zdavis1.springboottodo.Controller;

import com.zdavis1.springboottodo.Model.ToDoModel;
import com.zdavis1.springboottodo.Service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class ToDoController {
    private ToDoService service;

    public ToDoController(ToDoService service) {
        this.service = service;
    }

    // CREATE new task. Learned to output status code 201 for successful creation
    @PostMapping("/tasks")
    public ResponseEntity<ToDoModel> createTasks(@RequestBody ToDoModel task) {
        ToDoModel newTask = service.createTask(task);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newTask);
    }

    // RETRIEVE all
    @GetMapping("/tasks")
    public List<ToDoModel> getTasks() {
        return service.retrieveAllTasks();
    }

    // RETRIEVE one
    @GetMapping("/tasks/{id}")
    public ResponseEntity<ToDoModel> getATask(@PathVariable Long id) {
        if (service.retrieveByID(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            ToDoModel task = service.retrieveByID(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(task);
        }
    }

    // UPDATE one task
    @PutMapping("/tasks/{id}")
    public ResponseEntity<ToDoModel> updateTask(@PathVariable Long id, @RequestBody ToDoModel task) {
        Boolean result = service.updateTask(id, task);

        if (result) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(task);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete one
    @DeleteMapping("tasks/{id}")
    public ResponseEntity<ToDoModel> deleteATask(@PathVariable Long id) {
        if (service.retrieveByID(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            service.deleteTask(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
