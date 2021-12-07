package com.moinul.ToDoApplication.service;

import com.moinul.ToDoApplication.common.Enum.*;
import com.moinul.ToDoApplication.common.utils.Time;
import com.moinul.ToDoApplication.dto.ToDoDTO;
import com.moinul.ToDoApplication.extension.MockTimeExtension;
import com.moinul.ToDoApplication.model.ToDo;
import com.moinul.ToDoApplication.repository.ToDoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;


import java.time.*;
import java.util.*;


@ExtendWith(MockitoExtension.class)
@ExtendWith(MockTimeExtension.class)
class ToDoServiceTest {
    
    @Mock
    ToDoRepository toDoRepository;
    
    @InjectMocks
    ToDoServiceimpl toDoService;
    
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
        toDo.setCreatedDate(Time.currentDateTime());
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
        toDoDTO.setId(1L);
        toDo.setId(1L);
        
        Mockito.when(toDoRepository.findById(toDoDTO.getId())).thenReturn(java.util.Optional.of(toDo));
    
        Mockito.when(toDoRepository.save(toDo)).thenReturn(RECORD_1);
    
    
        toDoDTO = toDoService.updateToDo(toDoDTO);
    
        Assertions.assertThat(toDoDTO.getDescription()).isEqualTo("new Description");
    }
    
    @Test
    void getToDo() {
        Mockito.when(toDoRepository.findById(1L)).thenReturn(java.util.Optional.of(RECORD_1));
    
        ToDo toDo = toDoService.getToDo(1L);
        Assertions.assertThat(toDo.getPriority()).isEqualTo(ToDoPriority.HIGH);
    }
    
    @Test
    void getToDoList() {
        List<ToDo> toDoList = new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2,RECORD_3));
        Mockito.when(toDoRepository.findAllByPriority(PageRequest.of(0, 2))).thenReturn(toDoList);
        
        List<ToDoDTO>toDoList1= toDoService.getToDoList();
        Assertions.assertThat(toDoList1.size()).isEqualTo(3);
    }
    
    @Test
    void getListByStatus() {
        List<ToDo> toDoList = new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2));
        Mockito.when(toDoRepository.findAllByStatus(ToDoStatus.TODO,PageRequest.of(0, 2))).thenReturn(toDoList);
    
        List<ToDoDTO>toDoList1= toDoService.getListByStatus(ToDoStatus.TODO);
        Assertions.assertThat(toDoList1.size()).isEqualTo(2);
    }
    
    @Test
    void deleteToDo() {
        Mockito.when(toDoRepository.findById(1L)).thenReturn(java.util.Optional.of(RECORD_1));
        //dummy test for delete operation
        toDoService.deleteToDo(1L);
        
    }
}