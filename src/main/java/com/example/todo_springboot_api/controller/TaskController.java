package com.example.todo_springboot_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_springboot_api.entity.Task;
import com.example.todo_springboot_api.service.TaskService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks() {
		List<Task> tasks = taskService.getAllTasks();
		
		if(tasks != null) {
			return ResponseEntity.ok(tasks);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
		Task task = taskService.getTaskById(id);
		
		if(task != null) {
			return ResponseEntity.ok(task);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		Task savedTask = taskService.createTask(task);
		
		if(savedTask != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTaskById(@PathVariable Long id, @RequestBody Task task) {
		Task savedTask = taskService.updateTaskById(id, task);
		
		if(savedTask != null) {
			return ResponseEntity.ok(savedTask);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
		boolean successful = taskService.deleteTaskById(id);
		
		if(successful) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
