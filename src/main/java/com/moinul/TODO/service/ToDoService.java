package com.moinul.TODO.service;

import com.moinul.TODO.dto.ToDoDTO;
import com.moinul.TODO.model.ToDo;
import com.moinul.TODO.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ToDoService {
    
    private ToDoRepository toDoRepository;
    
    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }
    
    public ToDo createToDo(ToDoDTO toDoDTO) {
        ToDo toDo =new ToDo();
        setToDoValue(toDoDTO,toDo);
        LocalDate createdDate = LocalDate.now();
        toDo.setCreatedDate(createdDate);
        return toDoRepository.save(toDo); //todo check validation before saving
    }
    
    public ToDoDTO updateToDo(ToDoDTO updtaedToDo) {
        ToDo toDo = toDoRepository.findById(updtaedToDo.getId()).orElseThrow(EntityNotFoundException::new);
        if(toDo != null){
            setToDoValue(updtaedToDo,toDo);
            System.out.println(toDo);
            toDoRepository.save(toDo);
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
    
    public List<ToDoDTO> getToDoList() {
        List<ToDo> toDoList;
        toDoList= toDoRepository.findAll();
        if(toDoList == null) return  Collections.emptyList();
        return toDoList.stream().map(ToDoDTO::new).collect(Collectors.toList());
    }
    
    public void deleteToDo(Long id) {
        ToDo toDo = toDoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(toDo != null){
            toDoRepository.delete(toDo);
        }
    }
    
    public void setToDoValue(ToDoDTO toDoDTO, ToDo toDo){
        toDo.setTitle(toDoDTO.getTitle());
        toDo.setDescription(toDoDTO.getDescription());
        toDo.setStatus(toDoDTO.getStatus());
        toDo.setPriority(toDoDTO.getPriority());
    }
}
