package com.example.crudtest.service;

import com.example.crudtest.entity.ToDo;
import com.example.crudtest.repository.TodoRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TodoServiceImpl implements TodoService{

    TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<ToDo> getTodos() {

        List<ToDo> toDoList = new ArrayList<>();
        todoRepository.findAll().forEach(toDoList::add );
        return toDoList;
    }

    @Override
    public ToDo getTodoById(UUID id) {
        System.out.println("-*-*-*-* "+ id);
        return todoRepository.findById(id).get();
    }

    @Override
    public ToDo insert(ToDo toDo) {
        return todoRepository.save(toDo);
    }

    @Override
    public void updateTodo(UUID id, ToDo toDo) {
        ToDo toDoFromDb = todoRepository.findById(id).get();
        toDoFromDb.setStatus(toDo.getStatus());
        toDoFromDb.setTitle(toDo.getTitle());
        toDoFromDb.setDescription(toDo.getDescription());
        todoRepository.save(toDoFromDb);

    }

    @Override
    public void deleteTodo(UUID todoId) {
        todoRepository.deleteById(todoId);
    }
}
