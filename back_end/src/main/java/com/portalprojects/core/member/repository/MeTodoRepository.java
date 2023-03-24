package com.portalprojects.core.member.repository;

import com.portalprojects.core.member.model.response.MeDetailTodoResponse;
import com.portalprojects.core.member.model.response.MeTodoResponse;
import com.portalprojects.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query(value = """
            SELECT a.id, a.code, a.name, a.status_todo FROM to_do a WHERE a.todo_id = :idTodo
            ORDER BY a.created_date DESC
            """, nativeQuery = true)
    List<MeDetailTodoResponse> getDetailTodo(@Param("idTodo") String idTodo);

}
