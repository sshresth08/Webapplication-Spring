package de.thk.rwoerzbe.toolbox.todolist.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import de.thk.rwoerzbe.toolbox.todolist.entities.Todo;
import de.thk.rwoerzbe.toolbox.todolist.services.RandomQuoteService;
import de.thk.rwoerzbe.toolbox.todolist.services.TodoListService;

/**
 * Controller class that serves the todo list tool
 */
@Controller
public class TodoListController {
    
    @Autowired
    private TodoListService todoListService;

    @Autowired
    private RandomQuoteService randomQuoteService;

    /**
     * Serves the web page of the todo list. Is triggered by a
     * <code>GET /todolist</code> request
     * 
     * @param model Model that contains placeholders and values
     * that are defined in the todolist.html template
     * @return "todolist" that hints to the todolist.html
     */
    @GetMapping("/todolist")
    public String todolist(Model model) {
        Iterable<Todo> todos = todoListService.getAllTodos();
        model.addAttribute("todos", todos);
        model.addAttribute("quote", randomQuoteService.getRandomQuote());
        return "todolist";
    }

    /**
     * Serves a delete request for a todolist item. Is triggered
     * by a <code>POST /todolist/id</code> request with body
     * <code>method=delete</code>.
     * 
     * @param id The id of the todo item to be deleted
     * @return <code>"redirect:/todolist"</code> such that the web page is 
     * rerendered without the deleted item
     */
    @DeleteMapping(value = "/todolist/{id}")
    public String deleteTodo(@PathVariable String id) {
        todoListService.deleteTodo(UUID.fromString(id));
        return "redirect:/todolist";
    }

    /**
     * Serves a post request for a new todolist item. Is triggered
     * by a <code>POST /todolist</code> request with a body containing
     * the form data (<code>description</code> and <code>priority</code>)
     * 
     * @param todo A {@link de.thk.rwoerzbe.toolbox.todolist.entities.Todo}
     * that contains the form data
     * @return <code>"redirect:/todolist"</code> such that the web page is 
     * rerendered with the new item
     */
    @PostMapping(value = "/todolist", consumes = "application/x-www-form-urlencoded")
    public String createTodo(/* no @RequestBody here */ Todo todo) {
        todoListService.createTodo(todo);
        return "redirect:/todolist";
    }

}