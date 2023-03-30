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
            SELECT a.id, a.code, a.name, a.priority_level, a.descriptions, a.deadline,
            (SELECT COUNT(1) FROM to_do d WHERE d.status_todo = 1 AND d.todo_id = a.id) AS number_todo_complete,
            (SELECT COUNT(1) FROM to_do d WHERE d.todo_id = a.id) AS number_todo
            FROM to_do a JOIN period_todo b ON a.id = b.todo_id
            JOIN period c ON b.period_id = c.id WHERE c.id = :idPeriod
            AND a.todo_list_id = :idTodoList ORDER BY a.priority_level
            """, nativeQuery = true)
    List<MeTodoResponse> getToDoByPeriodAndPriorityLevel(@Param("idPeriod") String idPeriod, @Param("idTodoList") String idTodoList);

    @Query(value = """
            SELECT a.id, a.code, a.name, a.status_todo FROM to_do a WHERE a.todo_id = :idTodo
            ORDER BY a.created_date DESC
            """, nativeQuery = true)
    List<MeDetailTodoResponse> getDetailTodo(@Param("idTodo") String idTodo);

    @Query(value = """
            SELECT COUNT(1) FROM to_do WHERE todo_id = :todoId AND status_todo = 1
            """, nativeQuery = true)
    Short countTodoComplete(@Param("todoId") String todoId);

    @Query(value = """
            SELECT COUNT(1) FROM to_do WHERE todo_id = :todoId
            """, nativeQuery = true)
    Short countTodoInCheckList(@Param("todoId") String todoId);

}
