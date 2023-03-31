package com.portalprojects.core.member.repository;

import com.portalprojects.core.member.model.response.MeLabelResponse;
import com.portalprojects.repository.LabelRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author thangncph26123
 */
public interface MeLabelRepository extends LabelRepository {

    @Query(value = """
            SELECT c.id, c.code, c.name, c.color_label FROM to_do a JOIN label_todo b ON a.id = b.todo_id
            JOIN label c ON b.label_id = c.id WHERE a.id = :idTodo
            """, nativeQuery = true)
    List<MeLabelResponse> getAllLabelByIdTodo(@Param("idTodo") String idTodo);

    @Query(value = """
            SELECT c.id, c.code, c.name, c.color_label 
            FROM label c JOIN label_project a ON c.id = a.label_id
            JOIN project b ON a.project_id = b.id 
            WHERE b.id = :idProject
            """, nativeQuery = true)
    List<MeLabelResponse> getAll(@Param("idProject") String idProject);

}
