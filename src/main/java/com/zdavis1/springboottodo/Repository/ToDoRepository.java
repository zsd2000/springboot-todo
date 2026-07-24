package com.zdavis1.springboottodo.Repository;

import com.zdavis1.springboottodo.Model.ToDoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Repository layer of ToDoApplication
 * Uses an H2 (in-memory) database
 * Handles data persistence and retrieval of ToDoModel entities
 */
@Repository
public interface ToDoRepository extends JpaRepository<ToDoModel, Long> {

    @Modifying
    @Query
            (value = "INSERT into TASKS(TITLE, DESCRIPTION, DUE_DATE, PRIORITY) VALUES" +
            "(?, ?, ?, ?)",nativeQuery = true)
    void insertTask(String title, String description, LocalDate dueDate, int priority);

    // findByPriority
    // findByCompleted
    // updateTask
    // deleteByID
}
