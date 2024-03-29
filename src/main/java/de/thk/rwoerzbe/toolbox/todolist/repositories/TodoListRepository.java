package de.thk.rwoerzbe.toolbox.todolist.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.thk.rwoerzbe.toolbox.todolist.entities.Todo;

/**
 * Standard interface for storing and retriving {@link de.thk.rwoerzbe.toolbox.todolist.entities.Todo}
 */
@Repository
public interface TodoListRepository extends CrudRepository<Todo, UUID> {
    
}