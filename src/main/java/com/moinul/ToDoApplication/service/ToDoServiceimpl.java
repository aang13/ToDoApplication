package com.moinul.ToDoApplication.service;

import com.moinul.ToDoApplication.common.Enum.ToDoStatus;
import com.moinul.ToDoApplication.common.utils.Time;
import com.moinul.ToDoApplication.dto.ToDoDTO;
import com.moinul.ToDoApplication.model.ToDo;
import com.moinul.ToDoApplication.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.*;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ToDoServiceimpl implements ToDoService{
    
    private ToDoRepository toDoRepository;
    
    @Autowired
    public ToDoServiceimpl(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }
    
    public ToDo createToDo(ToDoDTO toDoDTO) {
        ToDo toDo = new ToDo();
        setToDoValue(toDoDTO, toDo);
        
        toDo.setCreatedDate(Time.currentDateTime());
        return toDoRepository.save(toDo);
    }
    
    
    public ToDoDTO updateToDo(ToDoDTO updtaedToDo) {
        ToDo toDo = toDoRepository.findById(updtaedToDo.getId()).orElseThrow(EntityNotFoundException::new);
        if (toDo != null) {
            setToDoValue(updtaedToDo, toDo);
            toDoRepository.save(toDo);
        } else {
            throw new EntityNotFoundException();
        }
        
        return updtaedToDo;
    }
    
    public ToDo getToDo(Long id) {
        ToDo toDo = toDoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (toDo == null) {
            throw new EntityNotFoundException();
        }
        return toDo;
    }
    
    public List<ToDoDTO> getToDoList() {
        List<ToDo> toDoList = toDoRepository.findAllByPriority( PageRequest.of(0, 50));
        if (toDoList == null) return Collections.emptyList();
        return toDoList.stream().map(ToDoDTO::new).collect(Collectors.toList());
    }
    
    public List<ToDoDTO> getListByStatus(ToDoStatus status) {
        List<ToDo> toDoList = toDoRepository.findAllByStatus(status,PageRequest.of(0, 50));
        if (toDoList == null) return Collections.emptyList();
        return toDoList.stream().map(ToDoDTO::new).collect(Collectors.toList());
    }
    
    public void deleteToDo(Long id) {
        ToDo toDo = toDoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (toDo != null) {
            toDoRepository.delete(toDo);
        }
    }
    
    public void setToDoValue(ToDoDTO toDoDTO, ToDo toDo) {
        toDo.setTitle(toDoDTO.getTitle());
        toDo.setDescription(toDoDTO.getDescription());
        toDo.setStatus(toDoDTO.getStatus());
        toDo.setPriority(toDoDTO.getPriority());
    }
}
