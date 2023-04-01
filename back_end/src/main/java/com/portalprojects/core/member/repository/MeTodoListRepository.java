package com.portalprojects.core.member.repository;

import com.portalprojects.core.member.model.response.MeTodoListResponse;
import com.portalprojects.repository.TodoListRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author thangncph26123
 */
public interface MeTodoListRepository extends TodoListRepository {

    @Query(value = """
            SELECT a.id, a.code, a.name, a.index_todo_list FROM todo_list a 
            WHERE a.project_id = :idProject ORDER BY a.index_todo_list
            """, nativeQuery = true)
    List<MeTodoListResponse> getAllTodoList(@Param("idProject") String idProject);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE todo_list SET index_todo_list = :indexAfter WHERE id = :idTodoList
            """, nativeQuery = true)
    void updateIndexTodoList(@Param("idTodoList") String idTodoList, @Param("indexAfter") Integer indexAfter);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE todo_list SET index_todo_list = index_todo_list - 1 
            WHERE index_todo_list > :indexBefore AND index_todo_list <= :indexAfter
            """, nativeQuery = true)
    void updateIndexTodoListDecs(@Param("indexBefore") Integer indexBefore, @Param("indexAfter") Integer indexAfter);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE todo_list SET index_todo_list = index_todo_list + 1 
            WHERE index_todo_list < :indexBefore AND index_todo_list >= :indexAfter
            """, nativeQuery = true)
    void updateIndexTodoListAsc(@Param("indexBefore") Integer indexBefore, @Param("indexAfter") Integer indexAfter);

}
