package com.portalprojects.repository;

import com.portalprojects.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author thangncph26123
 */
@Repository(TodoRepository.NAME)
public interface TodoRepository  extends JpaRepository<Todo, String> {

    public static final String NAME = "BaseTodoRepository";
}
