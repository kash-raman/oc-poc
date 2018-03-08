package com.kashraman.todobootapp.repo;

import com.kashraman.todobootapp.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepo extends MongoRepository<ToDo,String> {
}
