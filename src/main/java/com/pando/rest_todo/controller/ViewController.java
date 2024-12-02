package com.pando.rest_todo.controller;

import com.pando.rest_todo.model.Todo;
import com.pando.rest_todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ViewController {

    private TodoService todoService;

    public ViewController( TodoService todoService) {
        this.todoService = todoService;
    }
    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Todo> todoList = todoService.findByUsername("ibo");
        model.addAttribute("todos", todoList);
        model.addAttribute("message", "Hello World from Spring Boot");
        return "index";
    }


    @RequestMapping("/websocket")
    public String viewWebSocketPage(Model model) {
        model.addAttribute("message", "Hello World from Spring Boot");
        return "websocket";
    }

}
