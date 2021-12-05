package com.moinul.TODO.repository;

import com.moinul.TODO.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    
    List<ToDo> findAll();
}
