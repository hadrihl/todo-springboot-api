package com.example.todo_springboot_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_springboot_api.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
