package com.zdavis1.springboottodo.Controller;

import com.zdavis1.springboottodo.Model.ToDoModel;
import com.zdavis1.springboottodo.Service.ToDoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
