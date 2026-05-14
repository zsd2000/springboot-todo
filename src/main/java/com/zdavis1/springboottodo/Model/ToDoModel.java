package com.zdavis1.springboottodo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

/**
 * Represents a task entity in the ToDoApplication
 * A task is made up of a title (String), description (String), due date (LocalDate), and priority (int)
 * Mapped to database as a JPA entity
 * "priority" was recently added (05132026)
 *
 * @author Zachary Davis
 * @date 05132026
 */
@Entity
public class ToDoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private LocalDate dueDate;
    private int priority;

    public ToDoModel() {}

    public ToDoModel (String title, String description, String dueDate, int priority) {
        this.title = title;
        this.description = description;
        this.dueDate = LocalDate.parse(dueDate);
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = LocalDate.parse(dueDate);
    }
    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
