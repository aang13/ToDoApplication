package com.moinul.TODO.repository;

import com.moinul.TODO.common.Enum.*;
import com.moinul.TODO.model.ToDo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    
    @Query(value="SELECT e FROM ToDo e ORDER BY (CASE WHEN e.priority = 'NONE' THEN 0 WHEN e.priority = 'LOW' then 1 WHEN e." +
            "priority = 'MEDIUM' then 2 WHEN e.priority='HIGH' then 3 else -1 END) desc , e.createdDate asc")
    List<ToDo> findAllByPriority();
    
    @Query(value="SELECT e FROM ToDo e where (e is null or e.status=:status) ORDER BY (CASE WHEN e.priority = 'NONE' THEN 0 WHEN e.priority = 'LOW' then 1 WHEN e." +
            "priority = 'MEDIUM' then 2 WHEN e.priority='HIGH' then 3 else -1 END) desc , e.createdDate asc ")
    List<ToDo>findAllByStatus(ToDoStatus status);
}
