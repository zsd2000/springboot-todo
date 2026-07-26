package com.zdavis1.springboottodo.Repository;

import com.zdavis1.springboottodo.Model.ToDoModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository layer of ToDoApplication
 * Uses an H2 database stored inside a file, as opposed to volatile memory
 * Handles data persistence and retrieval of ToDoModel entities
 * Custom SQL queries were added to demonstrate knowledge of basic SQL programming language
 * @author Zachary Davis
 * @version 07252026
 */
@Repository
public interface ToDoRepository extends JpaRepository<ToDoModel, Long> {

    /**
     * Custom query to insert a new task into the database
     * This replaces the provided "save" operation from the JpaRepository interface
     * @param title
     * @param description
     * @param dueDate
     * @param priority
     * @author Zachary Davis
     * @version 07242026
     */
    @Modifying
    @Transactional
    @Query
            (value = "INSERT into TASKS(TITLE, DESCRIPTION, DUE_DATE, PRIORITY) " +
                    "VALUES (?, ?, ?, ?)",nativeQuery = true)
    void insertTask(String title, String description, LocalDate dueDate, int priority);

    /**
     * Custom query to find all tasks that are completed
     * Demonstrates basic querying where there is an added condition over a simple lookup
     * @return List<ToDoModel> a list of completed tasks
     * @author Zachary Davis
     * @version 07242026
     */
    @Query
            (value = "SELECT * " +
                    "FROM TASKS " +
                    "WHERE COMPLETE=TRUE", nativeQuery = true)
    List<ToDoModel> findByCompleted();

    /**
     * Custom query to mark a task complete
     * Demonstrates a simple UPDATE call
     * @param id
     * @return int the number of rows updated. This should return 1, as each task has a unique id
     * @author Zachary Davis
     * @version 07252026
     */
    @Modifying
    @Transactional
    @Query
            (value = "UPDATE TASKS " +
                    "SET COMPLETE = ?2 " +
                    "WHERE ID = ?1", nativeQuery = true)
    int markComplete(long id);

    /**
     * Custom query to delete a completed task
     * Demonstrates a DELETE call where there is an added condition
     * @param id the unique id of the task
     * @return int the number of rows deleted
     * @author Zachary Davis
     * @version 07252026
     */
    @Modifying
    @Transactional
    @Query
            (value = "DELETE " +
                    "FROM TASKS " +
                    "WHERE ID = ?1 AND COMPLETE = true", nativeQuery = true)
    int deleteCompleted(long id);
}
