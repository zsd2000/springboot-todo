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
import java.util.Optional;


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

    @Test
    public void retrieveByID() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.ofNullable(task));
        ToDoModel result = service.retrieveByID(1L);
        Assertions.assertEquals(task, result);
    }

    @Test
    public void updateTask() {
        Mockito.when(repository.existsById(1L)).thenReturn(true);
        Mockito.when(repository.getReferenceById(1L)).thenReturn(task);
        Mockito.when(repository.save(task)).thenReturn(task);

        ToDoModel newTask = new ToDoModel("Test updateTask", "Method test", "10/11/2025");

        service.updateTask(1L, newTask);

        Mockito.verify(repository).existsById(1L);
        Mockito.verify(repository).getReferenceById(1L);
        Mockito.verify(repository).save(task);
    }
}