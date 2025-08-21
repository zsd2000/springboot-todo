package com.zdavis1.springboottodo.Controller;

import com.zdavis1.springboottodo.Model.ToDoModel;
import com.zdavis1.springboottodo.Service.ToDoService;
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

    // NEEDS REWORK. Learning how to send status 201 and map to new URL with object being returned.
    @PostMapping("/task")
    public ToDoModel createTasks(@RequestBody ToDoModel task) {
        return service.createTask(task);
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
