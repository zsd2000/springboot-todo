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

    @GetMapping("/tasks")
    public List<ToDoModel> getTasks() {
        return service.retrieveAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public ToDoModel getATask(@PathVariable Long id) {
        return service.retriveByID(id);
    }

    @DeleteMapping("tasks/{id}")
    public void deleteATask(@PathVariable Long id) {
        service.deleteTask(id);
    }
}
