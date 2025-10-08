package com.zdavis1.springboottodo.Service;

import com.zdavis1.springboottodo.Model.ToDoModel;
import com.zdavis1.springboottodo.Repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public ToDoModel retrieveByID(Long id) {
        return repository.findById(id).orElse(null);
    }

    // NEED TO IMPLEMENT UPDATE METHOD

    public void deleteTask(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        else throw new NoSuchElementException("Task with id " + id + " not found");
    }
}
