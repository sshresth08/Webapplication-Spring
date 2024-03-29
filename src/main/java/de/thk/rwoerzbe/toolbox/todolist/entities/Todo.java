package de.thk.rwoerzbe.toolbox.todolist.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Entity class that wraps a Todo list item's data
 */
@Entity
public class Todo {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    /**
     * @return The ID of a Todo
     */
    public UUID getId() {
        return id;
    }

    /**
     * @param id The ID of a Todo
     */
    public void setId(UUID id) {
        this.id = id;
    }

    private String description;

    /**
     * @return The todo's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The todo's description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    private int priority;

    /**
     * @param priority The todo's priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @return The todo's priority
     */
    public int getPriority() {
        return priority;
    }
    
}