package com.zdavis1.springboottodo.Service;

import com.zdavis1.springboottodo.Model.ToDoModel;
import com.zdavis1.springboottodo.Repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Handles all business logic of tasks (ToDoModel entities)
 * Interacts with the repository layer for CRUD operations
 * Uses Dependency Injection to insert repository layer
 */
@Service
public class ToDoService {
    private final ToDoRepository repository;

    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a task
     * @param task The task the user is adding to the database
     * @return ToDoModel The task that was saved
     */
    public ToDoModel createTask(ToDoModel task) {
        return repository.save(task);
    }

    /**
     * Retrieves all saved tasks in the database
     * @return List<ToDoModel> A list of all saved tasks
     */
    public List<ToDoModel> retrieveAllTasks() {
        return repository.findAll();
    }

    /**
     * Retrieves one task from the database
     * The task is only retrieved if it exists within the database, throwing an exception if null
     * @param id The unique ID assigned to the task
     * @return Optional<ToDoModel> Either the specified task or an exception, if the task is not found
     */
    public Optional<ToDoModel> retrieveByID(Long id) {
        Optional<ToDoModel> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional;
        }

        throw new NoSuchElementException();
    }

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
            existingTask.get().setDueDate(task.getDueDate());
            repository.save(existingTask.get());
            return existingTask;
        }

        throw new NoSuchElementException();
    }

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
}