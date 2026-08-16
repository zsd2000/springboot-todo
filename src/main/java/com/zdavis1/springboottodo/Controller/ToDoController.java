package com.zdavis1.springboottodo.Controller;

import com.zdavis1.springboottodo.Model.ToDoModel;
import com.zdavis1.springboottodo.Service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Handles all HTTP requests for the ToDoApplication
 * Interacts with the service layer to perform logic and store/retrieve data, managing flow between client and server
 * Uses Dependency Injection to insert service layer
 * @author Zachary Davis
 * @version 08012026
 */
@RestController
@RequestMapping("")
public class ToDoController {
    private final ToDoService service;

    public ToDoController(ToDoService service) {
        this.service = service;
    }

    /**
     * I want to add URL parameters here. Need to refactor to include this.
     * Zachary Davis
     * 08092026
     */

    // ------------------- CREATE -------------------
    /**
     * Create new task
     * Title, Due Date, and Priority MUST be passed
     * Due Date format must be: "YYYY-MM-DD"
     * @param task The task requested to be stored from the client
     * @return ResponseEntity<ToDoModel> The saved task and HTTP status code 201
     * @author Zachary Davis
     * @version 08012026
     */
    @PostMapping("/tasks")
    public ResponseEntity<ToDoModel> createTask(@RequestBody ToDoModel task) {
        try {
            service.createTask(task);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // For "personal" logging
            return ResponseEntity.badRequest().build(); // Trying ResponseEntity Builder
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(task);
    }

    // ------------------- RETRIEVE -------------------
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
     * Retrieves all completed tasks.
     * Uses custom SQL query.
     * @return A list of completed tasks
     * @author Zachary Davis
     * @version 08092026
     */
    @GetMapping("/tasks/completed")
    public List<ToDoModel> getCompletedTasks() {
        return service.retrieveAllCompleted();
    }

    // ------------------- UPDATE -------------------
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
     * Marks a single task complete
     * Uses custom SQL query
     * @param id The task to mark complete
     * @return The updated task
     */
    @PatchMapping("/tasks/{id}")
    public ResponseEntity<ToDoModel> markTaskComplete(@PathVariable Long id) {
        try {
            service.markTaskComplete(id);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.
                status(HttpStatus.OK).
                body(service.retrieveByID(id).get());
    }

    // ------------------- DELETE -------------------
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

    /**
     * Deletes a single, specified task ONLY if the task has been marked complete
     * Uses custom SQL query
     * @param id the task to delete
     * @return A void HTTP status build
     */
    @DeleteMapping("/tasks/complete/{id}")
    public ResponseEntity<Void> deleteCompletedTask(@PathVariable Long id) {
        try {
            service.deleteCompletedTask(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
