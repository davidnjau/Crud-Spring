package com.example.crudtest.controller;

import com.example.crudtest.entity.ToDo;
import com.example.crudtest.repository.TodoRepository;
import com.example.crudtest.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {

    TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/get_todos")
    public ResponseEntity<List<ToDo>> getAllTodos(){
        List<ToDo> toDoList = todoService.getTodos();
        return new ResponseEntity<>(toDoList, HttpStatus.OK);
    }

    @GetMapping({"/get_todo/{todoId}"})
    public ResponseEntity<ToDo> getTodo(@PathVariable UUID todoId){
        return new ResponseEntity<>(todoService.getTodoById(todoId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ToDo> saveTodo(@RequestBody ToDo toDo){
        ToDo toDo1 = todoService.insert(toDo);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("todo", "/api/v1/todo/" + toDo1.getId().toString());
        return new ResponseEntity<>(toDo1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/get_todo/{todoId}"})
    public ResponseEntity<ToDo> updateTodo(@PathVariable("todoId") UUID todoId, @RequestBody ToDo toDo){
        todoService.updateTodo(todoId, toDo);
        return new ResponseEntity<>(todoService.getTodoById(todoId), HttpStatus.OK);
    }

    @DeleteMapping({"/{todoId}"})
    public ResponseEntity<ToDo> deleteTodo(@PathVariable("todoId") UUID todoId){
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
