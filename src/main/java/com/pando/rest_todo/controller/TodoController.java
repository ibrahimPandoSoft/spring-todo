package com.pando.rest_todo.controller;

import java.util.List;

import com.pando.rest_todo.model.Greeting;
import com.pando.rest_todo.model.HelloMessage;
import com.pando.rest_todo.model.Todo;
import com.pando.rest_todo.service.TodoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class TodoController {


	private TodoService todoService;

	public TodoController( TodoService todoService) {
		this.todoService = todoService;
	}

//	@MessageMapping("/hello")
//	@SendTo("/topic/greetings")
//	public Greeting greeting(HelloMessage message) throws Exception {
//		Thread.sleep(1000); // simulated delay
//		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//	}

	@PostMapping("/post/hello")
	@MessageMapping("/post/hello")
	@SendTo("/topic/greetings")
	public Greeting postGreeting(@RequestBody HelloMessage message) throws Exception {
		System.out.println("Hello2, " + HtmlUtils.htmlEscape(message.getName()) + "!");
		Thread.sleep(1000); // simulated delay
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}


	@GetMapping("/users/{username}/todos")
//	@PreAuthorize("hasRole('USER') AND #username == authentication.principal.username")
	public List<Todo> retrieveTodos(@PathVariable String username) {
		return todoService.findByUsername(username);
	}

	@GetMapping("/users/{username}/todos/{id}")
	public Todo retrieveTodoById(@PathVariable String username,
							 @PathVariable int id) {
		return todoService.findById(id);
	}

	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username,
										   @PathVariable int id) {
		todoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable String username,
						   @PathVariable int id, @RequestBody Todo todo) {
		todo.setId(id);
		todoService.updateTodo(todo);
		return todo;
	}


	@MessageMapping("/todo-stream")
	@SendTo("/topic/todos")
	public Todo greeting(Todo todo) throws Exception {
		Thread.sleep(1000); // simulated delay
		return todo;
	}

	@PostMapping("/users/{username}/todos")
	public Todo createTodo(@PathVariable String username,
						   @RequestBody Todo todo) {
		todo.setUsername(username);
		todo.setId(null);

		return todoService.addTodo(todo);
	}


	@PostMapping("/add-todos")
	public Todo createNewTodo(@RequestBody Todo todo) {
//		todo.setUsername(username);
		System.out.println(
				"new todo:" + todo.toString());

		return todoService.addTodo(todo);
	}

}
