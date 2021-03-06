package com.example.hfthello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HftHelloApplication {

	String user = "default";
	ArrayList<String> todos = new ArrayList<>();

	@GetMapping("/")
	String errorPage(){

		return "this is not the page you are looking for";		
	}

	@GetMapping("/hello")
	String sayHello(){

		return "Hello Hft, Good Morning and very happy new year "+user;
	}

	@PostMapping("/user/{username}")
	public String setUser(@PathVariable("username") String username){

		user = username;
		return "user has been set to: "+user;
	
	}

	@PostMapping("/todos/{newtodo}")
	public String addTodo(@PathVariable("newtodo") String todo){

		todos.add(todo);
		System.out.println(" New Todo added just now: "+todo);
		return todo+" has been added to the new list";

	}

	@DeleteMapping("/todos/{todo}")
	String removeTodo(@PathVariable String todo) {

		todos.remove(todo);
		return todo + " has been added to the new list";

	}

	@PostMapping(value = "/todos/",consumes = "application/json")
	public String addTodos(@RequestBody List<String> new_todos) {

		System.out.println("Receiving: "+new_todos);
		todos.addAll(new_todos);
		return new_todos + " have been added to the new list";
	}

	@GetMapping("/todos")
	public List<String >listTodos(){

		return todos;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HftHelloApplication.class, args);
	}

}
