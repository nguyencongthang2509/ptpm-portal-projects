package com.portalprojects.core.member.repository;

import com.portalprojects.repository.AssignRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author thangncph26123
 */
public interface MeAssignRepository extends AssignRepository {

    @Query(value = """
            SELECT b.member_id FROM to_do a JOIN assign b ON a.id = b.todo_id
            WHERE a.id = :idTodo
            """, nativeQuery = true)
    List<String> getAllMemberByIdTodo(@Param("idTodo") String idTodo);
}
