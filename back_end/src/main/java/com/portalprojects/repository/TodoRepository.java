package com.portalprojects.repository;

import com.portalprojects.entity.Todo;
import com.portalprojects.infrastructure.projection.SimpleEntityProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author thangncph26123
 */
@Repository(TodoRepository.NAME)
public interface TodoRepository extends JpaRepository<Todo, String> {

    public static final String NAME = "BaseTodoRepository";

    @Query(value = """
            SELECT id, name FROM todo
            """, nativeQuery = true)
    List<SimpleEntityProj> findAllSimpleEntity();
}
