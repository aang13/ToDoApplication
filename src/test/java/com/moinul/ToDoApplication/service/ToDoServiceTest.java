package com.moinul.ToDoApplication.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moinul.ToDoApplication.common.Enum.*;
import com.moinul.ToDoApplication.dto.ToDoDTO;
import com.moinul.ToDoApplication.model.ToDo;
import com.moinul.ToDoApplication.repository.ToDoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {
    
    @Mock
    ToDoRepository toDoRepository;
    
    @InjectMocks
    ToDoService toDoService;
    
    ToDo RECORD_1 = new ToDo(1l, "Description1", "Title1", ToDoStatus.TODO, ToDoPriority.HIGH , LocalDateTime.parse("2021-12-06T02:07:53.962303"));
    ToDo RECORD_2 = new ToDo(2l, "Description2", "Title2",ToDoStatus.TODO, ToDoPriority.LOW , LocalDateTime.now());
    ToDo RECORD_3 = new ToDo(3l, "Description2", "Title3",ToDoStatus.DONE, ToDoPriority.NONE , LocalDateTime.now());

    
    @Test
    void createToDoTest() {
        ToDo toDo = new ToDo();
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setTitle("new Title");
        toDoDTO.setDescription("new Description");
        toDoDTO.setPriority(ToDoPriority.HIGH);
        toDoDTO.setStatus(ToDoStatus.TODO);
        toDoService.setToDoValue(toDoDTO,toDo);
        toDo.setCreatedDate(LocalDateTime.now());
        Mockito.when(toDoRepository.save(toDo)).thenReturn(RECORD_1);
        
        toDo = toDoService.createToDo(toDoDTO);
    
        Assertions.assertThat(toDo.getId()).isEqualTo(1L);
    }
    
    @Test
    void updateToDoTest() {
        ToDo toDo = new ToDo();
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setTitle("new Title");
        toDoDTO.setDescription("new Description");
        toDoDTO.setPriority(ToDoPriority.HIGH);
        toDoDTO.setStatus(ToDoStatus.TODO);
        toDoService.setToDoValue(toDoDTO,toDo);
    
        Mockito.when(toDoRepository.save(toDo)).thenReturn(RECORD_1);
    
    
        toDoDTO = toDoService.updateToDo(toDoDTO);
    
        Assertions.assertThat(toDoDTO.getDescription()).isEqualTo("new Description");
    }
    
    @Test
    void getToDo() {
    }
    
    @Test
    void getToDoList() {
    }
    
    @Test
    void getListByStatus() {
    }
    
    @Test
    void deleteToDo() {
    }
}