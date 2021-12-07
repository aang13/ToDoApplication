package com.moinul.ToDoApplication.service;

import com.moinul.ToDoApplication.dto.ToDoDTO;
import com.moinul.ToDoApplication.model.ToDo;

import java.util.List;

public interface ToDoService {
    
    public ToDo createToDo(ToDoDTO toDoDTO);
    
    public ToDoDTO updateToDo(ToDoDTO updtaedToDo);
    
    public ToDo getToDo(Long id);
    
    public List<ToDoDTO> getToDoList();
    
    public void deleteToDo(Long id);
    
    public void setToDoValue(ToDoDTO toDoDTO, ToDo toDo);
}
