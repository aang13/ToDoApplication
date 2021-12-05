package com.moinul.TODO.resource;

import com.moinul.TODO.dto.ToDoDTO;
import com.moinul.TODO.model.ToDo;
import com.moinul.TODO.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ToDoResource {
    private ToDoService toDoService;
    
    @Autowired
    public ToDoResource(ToDoService toDoService){
        this.toDoService = toDoService;
    }
    
    @PostMapping(value = "todo/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ToDoDTO createNewToDo(@Validated @RequestBody ToDoDTO toDo){
        return new ToDoDTO(toDoService.createToDo(toDo));
    }
    
    @PutMapping(value = "todo/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ToDoDTO updateToDo(@Validated @RequestBody ToDoDTO toDo){
        return toDoService.updateToDo(toDo);
    }
    
    @GetMapping(value = "todo/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ToDoDTO getToDo(@PathVariable Long id){
        return new ToDoDTO(toDoService.getToDo(id));
    }
    
    @GetMapping(value = "todo/getAll/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ToDoDTO> getToDoList(){
        return toDoService.getToDoList();
    }
    
    @DeleteMapping(value = "todo/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void DeletToDo(@RequestParam("id") Long id){
        toDoService.deleteToDo(id);
    }
    
    
}
