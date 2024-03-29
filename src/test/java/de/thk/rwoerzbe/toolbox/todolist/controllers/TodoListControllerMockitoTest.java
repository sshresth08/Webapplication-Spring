package de.thk.rwoerzbe.toolbox.todolist.controllers;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import de.thk.rwoerzbe.toolbox.todolist.entities.Todo;
import de.thk.rwoerzbe.toolbox.todolist.services.RandomQuoteService;
import de.thk.rwoerzbe.toolbox.todolist.services.TodoListService;

@WebMvcTest(TodoListController.class)
class TodoListControllerMockitoTest {

    @MockBean
    private TodoListService todoListService;

    @MockBean
    private RandomQuoteService randomQuoteService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTodos_PageContainsTodo() throws Exception {
        // 1 fixture setup: prepare TodoListService mock
        final String DUMMY_DESCRIPTION = "Dummy Description";
        List<Todo> todoList = new ArrayList<>();
        Todo todo = new Todo();
        todo.setDescription(DUMMY_DESCRIPTION);
        todo.setPriority(1);
        todoList.add(todo);
        Mockito.when(todoListService.getAllTodos()).thenReturn(todoList);

        // 2 exercise subject under test (SUT)
        // We do not call TodoListController.get(Model) directly but simulate
        // an incoming HTTP GET request to /todolist
        ResultActions resultActions = mockMvc.perform(get("/todolist"));

        // 3 result verification
        // 3.1 Is HTTP status code equal to 200 (OK)?
        resultActions.andExpect(status().isOk());
        // 3.2.1 Check if the DUMMY_DESCRIPTION is where it is supposed to be
        resultActions.andExpect(xpath("/html/body/table/tbody/tr[1]/td[3]").string(DUMMY_DESCRIPTION));
        // 3.2.2 Check if TodoListRepository.findAll() has actually been called once
        Mockito.verify(todoListService, times(1)).getAllTodos();        
    }

    @Test
    void getTodos_PageContainsQuote() throws Exception {
        // TODO 1 fixture setup: prepare RandomServiceMock s. t. it always returns "Dummy Quote"

        // 2 exercise subject under test (SUT)
        // We do not call TodoListController.get(Model) directly but simulate
        // an incoming HTTP GET request to /todolist

        // 3 result verification
        // 3.1 Is HTTP status code equal to 200 (OK)?

        // TODO 3.2.1 Is the text under html/body/span really "Dummy Quote"?
        // Hint: The xpath expression is "/html/body/span/text()"

        // TODO 3.2.2 Check if the RandomQuoteService has actually been called once

    }

}
