package com.example.crudtest.service;

import com.example.crudtest.entity.ToDo;

import java.util.List;
import java.util.UUID;

public interface TodoService {

    List<ToDo> getTodos();
    ToDo getTodoById(UUID id);
    ToDo insert(ToDo toDo);
    void updateTodo(UUID id, ToDo toDo);
    void deleteTodo(UUID todoId);

}
