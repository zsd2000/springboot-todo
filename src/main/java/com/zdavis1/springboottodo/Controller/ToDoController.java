package com.zdavis1.springboottodo.Controller;

import com.zdavis1.springboottodo.Model.ToDoModel;
import com.zdavis1.springboottodo.Service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("")
public class ToDoController {
    private ToDoService service;

    public ToDoController(ToDoService service) {
        this.service = service;
    }

    // Create new task, outputting the object and status code 201
    @PostMapping("/task")
    public ResponseEntity<ToDoModel> createTasks(@RequestBody ToDoModel task) {
        ToDoModel newTask = service.createTask(task);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newTask);
    }

    // GET all
    @GetMapping("/task")
    public List<ToDoModel> getTasks() {
        return service.retrieveAllTasks();
    }

    // GET one
    @GetMapping("/task/{id}")
    public ToDoModel getATask(@PathVariable Long id) {
        return service.retriveByID(id);
    }

    // Delete one
    @DeleteMapping("task/{id}")
    public void deleteATask(@PathVariable Long id) {
        service.deleteTask(id);
    }
}
