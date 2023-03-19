package com.portalprojects.core.member.repository;

import com.portalprojects.core.member.model.response.MeTodoResponse;
import com.portalprojects.repository.TodoRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author thangncph26123
 */
public interface MeTodoRepository extends TodoRepository {

    @Query(value = """
            SELECT a.id, a.code, a.name, a.priority_level FROM to_do a JOIN period_todo b ON a.id = b.todo_id
            JOIN period c ON b.period_id = c.id WHERE c.id = :idPeriod
            AND a.status_todo = :statusTodo ORDER BY a.priority_level
            """, nativeQuery = true)
    List<MeTodoResponse> getToDoByPeriodAndPriorityLevel(@Param("idPeriod") String idPeriod, @Param("statusTodo") Integer statusTodo);
}
