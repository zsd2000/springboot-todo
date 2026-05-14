package com.zdavis1.springboottodo.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Represents a task entity in the ToDoApplication
 * A task is made up of a title (String), description (String), due date (LocalDate), priority (int), and completed
 * (boolean)
 * Mapped to database as a JPA entity
 * "priority" and "completed" was recently added (05132026)
 *
 * @author Zachary Davis
 * @date 05132026
 */
@Entity
@Table(name = "TASKS")
public class ToDoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "COMPLETE")
    private boolean complete;

    @Column(name = "TITLE", nullable = false, length = 50)
    private String title;

    @Column(name = "DESCRIPTION",length = 100)
    private String description;

    @Column(name = "DUE_DATE", nullable = false)
    private LocalDate dueDate;

    @Column(name = "PRIORITY", nullable = false)
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

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
