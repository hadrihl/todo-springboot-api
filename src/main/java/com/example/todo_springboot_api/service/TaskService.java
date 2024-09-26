package com.example.todo_springboot_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.todo_springboot_api.entity.Task;
import com.example.todo_springboot_api.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}
	
	public Task getTaskById(Long id) {
		return taskRepository.findById(id).get();
	}
	
	public Task createTask(Task task) {
		return taskRepository.save(task);
	}
	
	public Task updateTaskById(Long id, Task task) {
		Task savedTask = taskRepository.findById(id).get();
		
		savedTask.setTitle(task.getTitle());
		savedTask.setDescription(task.getDescription());
		savedTask.setPriority(task.getPriority());
		savedTask.setCompleted(task.isCompleted());
		
		return taskRepository.save(savedTask);
	}
	
	public boolean deleteTaskById(Long id) {
		if(taskRepository.getReferenceById(id) != null) {
			taskRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
