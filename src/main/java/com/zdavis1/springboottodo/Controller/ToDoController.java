package com.zdavis1.springboottodo.Controller;

import com.zdavis1.springboottodo.Model.ToDoModel;
import com.zdavis1.springboottodo.Service.ToDoService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Handles all HTTP requests for the ToDoApplication
 * Interacts with the service layer to perform logic and store/retrieve data, managing flow between client and server
 * Uses Dependency Injection to insert service layer
 */
@RestController
@RequestMapping("")
public class ToDoController {
    private ToDoService service;

    public ToDoController(ToDoService service) {
        this.service = service;
    }

    /**
     * Creates/stores a task
     * @param task The task requested to be stored from the client
     * @return ResponseEntity<ToDoModel> The saved task and HTTP status code 201
     */
    @PostMapping("/tasks")
    public ResponseEntity<ToDoModel> createTasks(@RequestBody ToDoModel task) {
        ToDoModel newTask = service.createTask(task);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newTask);
    }

    @PostMapping("/tasks")
    public ResponseEntity<ToDoModel> createTaskCustom(@RequestBody ToDoModel task) {

        try {
            service.createTaskCustom(task);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(task);
    }

    /**
     * Retrieves all saved tasks
     * @return List<ToDoModel> A list of all saved tasks
     */
    @GetMapping("/tasks")
    public List<ToDoModel> getTasks() {
        return service.retrieveAllTasks();
    }

    /**
     * Retrieves a specific task
     * @param id The unique ID of the requested task
     * @return ResponseEntity<ToDoModel> The desired task and HTTP status code 200 if found, 404 if not found
     */
    @GetMapping("/tasks/{id}")
    public ResponseEntity<ToDoModel> getATask(@PathVariable Long id) {
        try {
            ToDoModel task = service.retrieveByID(id).get();
            return ResponseEntity.ok(task);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Retrieves, updates, and saves a specific task
     * @param id The unique ID of the requested task
     * @param task The updated task information
     * @return ResponseEntity<ToDoModel> The updated task and HTTP status code 200 if found, 404 if not found
     */
    @PutMapping("/tasks/{id}")
    public ResponseEntity<ToDoModel> updateTask(@PathVariable Long id, @RequestBody ToDoModel task) {
        try {
            ToDoModel updateTask = service.updateTask(id, task).get();
            return ResponseEntity.ok(updateTask);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Deletes task
     * @param id The unique ID of the requested task
     * @return ResponseEntity<Void> Sends HTTP status code 204 if deleted, 404 if not found
     */
    @DeleteMapping("tasks/{id}")
    public ResponseEntity<Void> deleteATask(@PathVariable Long id) {
        try {
            ToDoModel deleteTask = service.deleteTask(id).get();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
