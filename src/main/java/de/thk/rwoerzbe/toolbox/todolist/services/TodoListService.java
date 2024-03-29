package de.thk.rwoerzbe.toolbox.todolist.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thk.rwoerzbe.toolbox.todolist.entities.Todo;
import de.thk.rwoerzbe.toolbox.todolist.repositories.TodoListRepository;

/**
 * Service class that provides operations for managing a list of todos.
 */
@Service
public class TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;
    
    /**
     * Retrieves all the todos in the list.
     *
     * @return An Iterable of Todo objects representing all the todos in the list.
     */
    public Iterable<Todo> getAllTodos() {
        return todoListRepository.findAll();
    }

    /**
     * Creates a new todo and adds it to the list.
     *
     * @param newTodo The Todo object to be added to the list.
     * @return The created Todo object with assigned values, including a unique ID.
     */
    public Todo createTodo(Todo newTodo) {
        return todoListRepository.save(newTodo);
    }

    /**
     * Deletes a todo from the list based on its unique ID.
     *
     * @param id The unique ID of the todo to be deleted.
     */
    public void deleteTodo(UUID id) {
        todoListRepository.deleteById(id);
    }
    
}