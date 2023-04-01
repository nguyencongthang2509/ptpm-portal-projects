package com.portalprojects.util;

import com.portalprojects.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author thangncph26123
 */
@Component
public class TodoListHelper {

    @Autowired
    @Qualifier(TodoListRepository.NAME)
    private TodoListRepository todoListRepository;

    public String genCodeTodoList(String projectId) {
        Integer countPeriod = todoListRepository.countSimpleEntityByIdProject(projectId);
        Integer newCountPeriod = ++countPeriod;
        return "TodoList_" + newCountPeriod;
    }
}
