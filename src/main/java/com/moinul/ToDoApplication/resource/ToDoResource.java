package com.moinul.ToDoApplication.resource;

import com.moinul.ToDoApplication.common.Enum.ToDoStatus;
import com.moinul.ToDoApplication.dto.ToDoDTO;
import com.moinul.ToDoApplication.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    
    @GetMapping(value = "todo/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ToDoDTO> getToDoList(@RequestParam Optional<String>status){
        
        if(status.isPresent()) {
            ToDoStatus toDoStatus = ToDoStatus.valueOf(status.orElse(""));
            return toDoService.getListByStatus(toDoStatus);
        }
        else{
            return toDoService.getToDoList();
        }
    }
    
    @DeleteMapping(value = "todo/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void DeleteToDo(@RequestParam("id") Long id){
        toDoService.deleteToDo(id);
    }
    
}
