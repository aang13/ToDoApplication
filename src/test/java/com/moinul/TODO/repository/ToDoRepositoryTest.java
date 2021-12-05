package com.moinul.TODO.repository;

import com.moinul.TODO.common.Enum.*;
import com.moinul.TODO.model.ToDo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ToDoRepositoryTest {
    
    @Autowired
    ToDoRepository toDoRepository;
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveToDoTest(){
        
        ToDo toDo = new ToDo();
        toDo.setTitle("title");
        toDo.setDescription("description");
        toDo.setStatus(ToDoStatus.TODO);
        toDo.setPriority(ToDoPriority.NONE);
        
        toDoRepository.save(toDo);
        
        assertThat(toDo.getId()).isGreaterThan(0);
    }
    
    @Test
    @Order(2)
    public void getToDoTest(){
    
        ToDo toDo = toDoRepository.findById(1L).get();
        
        assertThat(toDo.getId()).isEqualTo(1L);
        
    }
    
    @Test
    @Order(3)
    public void getListOfEmployeesTest(){
        
        List<ToDo> employees = toDoRepository.findAll();
        
       assertThat(employees.size()).isGreaterThan(0);
        
    }
    
    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateToDoTest(){
    
        ToDo employee = toDoRepository.findById(1L).get();
        
        employee.setPriority(ToDoPriority.HIGH);
        
        ToDo toDoUpdated =  toDoRepository.save(employee);
        
       assertThat(toDoUpdated.getPriority()).isEqualTo(ToDoPriority.HIGH);
        
    }
    
    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteToDoTest(){
    
        ToDo toDo = toDoRepository.findById(1L).get();
        
        toDoRepository.delete(toDo);
    
    
        ToDo toDo1 = null;
        
        Optional<ToDo> optionalToDo = toDoRepository.findById(1L);
        
        if(optionalToDo.isPresent()){
            toDo1 = optionalToDo.get();
        }
        assertThat(toDo1).isNull();
    }
    
}