package com.pando.rest_todo.service;

import com.pando.rest_todo.model.Todo;
import com.pando.rest_todo.repository.TodoRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoService.class);

    private static List<Todo> todos = new ArrayList<>();
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findByUsername(String username) {
        return todoRepository.findByUsername(username);
    }

    public Todo findById(int id) {
        return todoRepository.findById(id).get();
    }

    public void deleteById(int id) {
        todoRepository.deleteById(id);
    }

    public Todo updateTodo(@Valid Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);

    }
}