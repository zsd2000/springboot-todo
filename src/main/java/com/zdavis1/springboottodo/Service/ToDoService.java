package com.zdavis1.springboottodo.Service;

import com.zdavis1.springboottodo.Model.ToDoModel;
import com.zdavis1.springboottodo.Repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ToDoService {
    private final ToDoRepository repository;

    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    public ToDoModel createTask(ToDoModel task) {
        return repository.save(task);
    }

    public List<ToDoModel> retrieveAllTasks() {
        return repository.findAll();
    }

    public Optional<ToDoModel> retrieveByID(Long id) {
        Optional<ToDoModel> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional;
        }

        throw new NoSuchElementException();
    }

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

    public Optional<ToDoModel> deleteTask(Long id) {
        Optional<ToDoModel> task = retrieveByID(id);

        repository.delete(task.get());

        return task;
    }
}