package com.moinul.TODO.dto;

import com.moinul.TODO.common.Enum.*;
import com.moinul.TODO.model.ToDo;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToDoDTO {
    
    private Long id;
    
    private String description;
    
    private String title;
    
    private ToDoStatus status;
    
    private ToDoPriority priority;
    
    
    public ToDoDTO(ToDo toDo) {
        this(toDo.getId(), toDo.getDescription(),toDo.getTitle(),toDo.getStatus(),toDo.getPriority());
    }
}
