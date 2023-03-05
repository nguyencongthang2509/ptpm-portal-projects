package com.portalprojects.repository;

import com.portalprojects.entity.LabelTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author thangncph26123
 */
@Repository(LabelTodoRepository.NAME)
public interface LabelTodoRepository  extends JpaRepository<LabelTodo, String> {

    public static final String NAME = "BaseLabelTodoRepository";
}
