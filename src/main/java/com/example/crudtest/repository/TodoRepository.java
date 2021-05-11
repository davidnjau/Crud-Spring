package com.example.crudtest.repository;

import com.example.crudtest.entity.ToDo;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TodoRepository extends CrudRepository<ToDo, UUID> {
}
