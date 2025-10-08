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
    @PostMapping("/task")
    public ResponseEntity<ToDoModel> createTasks(@RequestBody ToDoModel task) {
        ToDoModel newTask = service.createTask(task);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newTask);
    }

    // RETRIEVE all
    @GetMapping("/task")
    public List<ToDoModel> getTasks() {
        return service.retrieveAllTasks();
    }

    // RETRIEVE one
    @GetMapping("/task/{id}")
    public ToDoModel getATask(@PathVariable Long id) {
        return service.retrieveByID(id);
    }

    // UPDATE one task
    @PutMapping("/task/{id}")
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
    @DeleteMapping("task/{id}")
    public void deleteATask(@PathVariable Long id) {
        service.deleteTask(id);
    }
}
