package com.moinul.ToDoApplication.model;

import com.moinul.ToDoApplication.common.Enum.*;
import lombok.*;


import javax.persistence.*;
import java.time.*;

@Entity
@Table(name="todo")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ToDo {
    //todo add validation of sizes for each attribute
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String description;
    
    private String title;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ToDoStatus status;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private ToDoPriority priority;
    
    @Column(name ="created_date")
    private LocalDateTime createdDate;
    
    
}
