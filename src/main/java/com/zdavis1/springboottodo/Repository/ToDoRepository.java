package com.zdavis1.springboottodo.Repository;

import com.zdavis1.springboottodo.Model.ToDoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository layer of ToDoApplication
 * Uses an H2 (in-memory) database
 * Handles data persistence and retrieval of ToDoModel entities
 */
@Repository
public interface ToDoRepository extends JpaRepository<ToDoModel, Long> {
}
