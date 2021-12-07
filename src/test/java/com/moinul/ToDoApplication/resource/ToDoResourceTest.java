package com.moinul.ToDoApplication.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moinul.ToDoApplication.common.Enum.*;
import com.moinul.ToDoApplication.dto.ToDoDTO;
import com.moinul.ToDoApplication.model.ToDo;
import com.moinul.ToDoApplication.service.ToDoServiceimpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ToDoResource.class)
class ToDoResourceTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    ObjectMapper mapper;
    
    @MockBean
    ToDoServiceimpl toDoService;
    
    ToDo RECORD_1 = new ToDo(1l, "Description1", "Title1",ToDoStatus.TODO, ToDoPriority.HIGH , LocalDateTime.now());
    ToDo RECORD_2 = new ToDo(2l, "Description2", "Title2",ToDoStatus.TODO, ToDoPriority.LOW , LocalDateTime.now());
    ToDo RECORD_3 = new ToDo(3l, "Description2", "Title3",ToDoStatus.DONE, ToDoPriority.NONE , LocalDateTime.now());
    
    
    @Test
    void createNewToDo() throws Exception {
        Mockito.when(toDoService.createToDo(new ToDoDTO(RECORD_1))).thenReturn(RECORD_1);
    
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/todo/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(RECORD_1));
    
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.description", is("Description1")));
    }
    
    @Test
    void updateToDo() throws Exception {
        ToDo updatedRecord = new ToDo(5L,"newTitle","newDescription", ToDoStatus.TODO, ToDoPriority.MEDIUM, RECORD_1.getCreatedDate());
        ToDoDTO updatedRecordDTO = new ToDoDTO(5L,"newTitle","newDescription", ToDoStatus.TODO, ToDoPriority.MEDIUM);
    
        Mockito.when(toDoService.getToDo(RECORD_1.getId())).thenReturn(RECORD_1);
        Mockito.when(toDoService.createToDo(updatedRecordDTO)).thenReturn(updatedRecord);
    
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/todo/update")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedRecord));
    
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }
    
    @Test
    void getToDo() throws Exception {
        Mockito.when(toDoService.getToDo(RECORD_1.getId())).thenReturn(RECORD_1);
    
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/todo/get/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }
    
    @Test
    void getToDoList() throws Exception {
        List<ToDo> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        Mockito.when(toDoService.getToDoList()).thenReturn(records.stream().map(ToDoDTO::new).collect(Collectors.toList()));
    
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/todo/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }
    
    @Test
    void getToDoListWIthStatusTest() throws Exception {
        List<ToDo> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        
        List<ToDo>toDoRecords = records.stream().filter(r-> r.getStatus() ==ToDoStatus.TODO).collect(Collectors.toList());
    
        Mockito.when(toDoService.getListByStatus(ToDoStatus.TODO)).thenReturn(toDoRecords.stream().map(ToDoDTO::new).collect(Collectors.toList()));
        
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/todo/getAll?status=TODO")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
    
    @Test
    void deleteToDo() throws Exception {
        toDoService.deleteToDo(2L);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/odo/delete/2"));
    }
}