package com.portalprojects.repository;

import com.portalprojects.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author thangncph26123
 */
@Repository
public interface TodoListRepository extends JpaRepository<TodoList, String> {

    public static final String NAME = "BaseTodoRepository";
}
