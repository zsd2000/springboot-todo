package com.zdavis1.springboottodo.Service;

import com.zdavis1.springboottodo.Model.ToDoModel;
import com.zdavis1.springboottodo.Repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Handles all business logic of tasks (ToDoModel entities)
 * Interacts with the repository layer for CRUD operations
 * Uses Dependency Injection to insert repository layer
 * Added additional methods to use custom SQL queries
 * Input validation added, throwing an exception to the Controller layer if invalid input is passed
 * @author Zachary Davis
 * @version 07252026
 */
@Service
public class ToDoService {
    private final ToDoRepository repository;

    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    // ------------------- CREATE -------------------
    /**
     * Insert a new task into the database
     * Utilizes a custom SQL query
     * Throws an IllegalArgumentException if input is invalid
     * @param task task object to be inserted into the DB
     * @return true if task is added
     * @author Zachary Davis
     * @version 07252026
     */
    public void createTask(ToDoModel task) {
        String title = task.getTitle();
        String description = task.getDescription();
        LocalDate dueDate = task.getDueDate();
        int priority = task.getPriority();

        if (title == null) {
            throw new IllegalArgumentException("Title must not be null");
        }
        if (dueDate == null) {
            throw new IllegalArgumentException("Due Date must not be null");
        }
        if (priority < 1 || priority > 3) {
            throw new IllegalArgumentException("Priority must be between 1 and 3");
        }

        repository.insertTask(title, description, dueDate, priority);
    }

    // ------------------- RETRIEVE -------------------
    /**
     * Retrieves all saved tasks in the database
     * @return List<ToDoModel> A list of all saved tasks
     * @author Zachary Davis
     */
    public List<ToDoModel> retrieveAllTasks() {
        return repository.findAll();
    }

    /**
     * Retrieves one task from the database
     * The task is only retrieved if it exists within the database, throwing an exception if null
     * @param id The unique ID assigned to the task
     * @return Optional<ToDoModel> Either the specified task or an exception, if the task is not found
     * @author Zachary Davis
     */
    public Optional<ToDoModel> retrieveByID(Long id) {
        Optional<ToDoModel> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional;
        }

        throw new NoSuchElementException();
    }

    /**
     * Retrieve all completed tasks in the DB
     * Utilizes custom SQL query
     * @return a list of completed Tasks
     * @author Zachary Davis
     * @version 07252026
     */
    public List<ToDoModel> retrieveAllCompleted() {
        return repository.findByCompleted();
    }

    // ------------------- UPDATE -------------------
    /**
     * Updates a previously saved task
     * @param id The unique ID assigned to the task
     * @param task The updated task information
     * @return Optional<ToDoModel> Either the updated task or an exception, if the desired task to be updated is not found
     */
    public Optional<ToDoModel> updateTask(Long id, ToDoModel task) {
        Optional<ToDoModel> existingTask = retrieveByID(id);

        if (existingTask.isPresent()) {
            existingTask.get().setTitle(task.getTitle());
            existingTask.get().setDescription(task.getDescription());
            existingTask.get().setDueDate(task.getDueDate().toString());
            repository.save(existingTask.get());
            return existingTask;
        }

        throw new NoSuchElementException();
    }

    public boolean markTaskComplete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must not be null");
        }

        int result = repository.markComplete(id);

        if (result != 1) {
            throw new NoSuchElementException("Task not found");
        }

        return true;
    }

    // ------------------- DELETE -------------------
    /**
     * Deletes the specified task
     * @param id The unique ID assigned to the task
     * @return Optional<ToDoModel> Either the deleted task or an exception, if the desired task to be deleted is not found
     */
    public Optional<ToDoModel> deleteTask(Long id) {
        Optional<ToDoModel> task = retrieveByID(id);

        repository.delete(task.get());

        return task;
    }

    public Optional<ToDoModel> deleteCompletedTask(Long id)
}