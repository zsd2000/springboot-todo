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
import java.util.NoSuchElementException;
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
    public void retrieveTask() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(task));
        Optional<ToDoModel> result = service.retrieveByID(1L);
        Assertions.assertEquals(task, result.get());

        Mockito.when(repository.findById(2L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> {service.retrieveByID(2L);});
    }

    @Test
    public void updateTaskSuccess() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(task));
        Mockito.when(repository.save(task)).thenReturn(task);

        Optional<ToDoModel> updateTask = service.updateTask(1L, task);
        Mockito.verify(repository, Mockito.times(1)).save(task);
        Assertions.assertEquals(task, updateTask.get());
    }

    @Test
    public void updateTaskFailure() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> {service.updateTask(1L, task);});
    }

    @Test
    public void deleteTaskSuccess() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(task));
        service.deleteTask(1L);
        Mockito.verify(repository, Mockito.times(1)).delete(task);
    }

    @Test
    public void deleteTaskFailure() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> {service.deleteTask(1L);});
    }
}