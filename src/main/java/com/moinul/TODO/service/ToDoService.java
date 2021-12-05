package com.moinul.TODO.service;

import com.moinul.TODO.model.ToDo;
import com.moinul.TODO.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class ToDoService {
    
    private ToDoRepository toDoRepository;
    
    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }
    
    public ToDo createToDo(ToDo toDo) {
        return toDoRepository.save(toDo); //todo check validation before saving
    }
    
    public ToDo updateToDo(ToDo updtaedToDo) {
        ToDo toDo = toDoRepository.getById(updtaedToDo.getId());
        if(toDo != null){
            toDoRepository.save(updtaedToDo);
        }
        else{
            throw new EntityNotFoundException();
        }
        
        return updtaedToDo;
    }
    
    public ToDo getToDo(Long id) {
        ToDo toDo = toDoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(toDo == null){
            throw new EntityNotFoundException();
        }
        return toDo;
    }
    
    public List<ToDo> getToDoList() {
        List<ToDo> toDoList = new ArrayList<>();
        toDoList= toDoRepository.findAll();
        if(toDoList == null) return  Collections.emptyList();
        return toDoList;
    }
    
    public void deleteToDo(Long id) {
        ToDo toDo = toDoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(toDo != null){
            toDoRepository.delete(toDo);
        }
    }
}
