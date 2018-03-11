package com.kashraman.todobootapp.controller;

import com.kashraman.todobootapp.model.ToDo;
import com.kashraman.todobootapp.repo.ToDoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class ToDoController {
    @Autowired
    ToDoRepo toDoRepo;

    @GetMapping("todos")
    public List<ToDo> getAll() {
        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
        return toDoRepo.findAll(sort);

    }

    @PostMapping("/todos")
    public ToDo createTodo(@Valid @RequestBody ToDo todo) {
        todo.setCompleted(false);
        return toDoRepo.save(todo);
    }

    @GetMapping(value = "/todos/{id}")
    public ResponseEntity<ToDo> getTodoById(@PathVariable("id") String id) {
        return toDoRepo.findById(id)
                .map(todo -> ResponseEntity.ok().body(todo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/todos/{id}")
    public ResponseEntity<ToDo> updateTodo(@PathVariable("id") String id,
                                           @Valid @RequestBody ToDo todo) {
        return toDoRepo.findById(id)
                .map(todoData -> {
                    todoData.setTitle(todo.getTitle());
                    todoData.setCompleted(todo.isCompleted());
                    ToDo updatedTodo = toDoRepo.save(todoData);
                    return ResponseEntity.ok().body(updatedTodo);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") String id) {
        return toDoRepo.findById(id)
                .map(todo -> {
                    toDoRepo.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
