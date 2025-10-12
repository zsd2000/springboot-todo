package com.zdavis1.springboottodo.Service;

import com.zdavis1.springboottodo.Model.ToDoModel;
import com.zdavis1.springboottodo.Repository.ToDoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


@ExtendWith(MockitoExtension.class)
public class ToDoServiceTest {
    private ToDoModel task;

    @Mock
    private ToDoRepository repository;

    @InjectMocks
    private ToDoService service;

    @BeforeEach
    public void setUp() {
        task = new ToDoModel("Test", "Task 1", "10/11/2025");
    }

    @Test
    public void createTask() {
        Mockito.when(repository.save(task)).thenReturn(task);
        ToDoModel result = service.createTask(task);
        Assertions.assertEquals(result, repository.save(task));
    }

    @Test
    public void retrieveAllTasks() {
        Mockito.when(repository.findAll()).thenReturn(List.of(task));
        List<ToDoModel> result = service.retrieveAllTasks();
        Assertions.assertEquals(1, result.size());
    }
}
