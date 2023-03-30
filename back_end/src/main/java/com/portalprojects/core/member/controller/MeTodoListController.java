package com.portalprojects.core.member.controller;

import com.portalprojects.core.common.base.ResponseObject;
import com.portalprojects.core.member.model.request.MeCreateOrDeleteAssignRequest;
import com.portalprojects.core.member.model.request.MeUpdateTodoListRequest;
import com.portalprojects.core.member.service.MeTodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thangncph26123
 */
@RestController
@RequestMapping("/member/todo-list")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class MeTodoListController {

    @Autowired
    private MeTodoListService meTodoListService;

    @GetMapping("/{id}")
    public ResponseObject getAllTodoList(@PathVariable("id") String id){
        return new ResponseObject(meTodoListService.getAllTodoList(id));
    }

    @MessageMapping("/update-todo-list/{projectId}/{periodId}")
    @SendTo("/portal-projects/todo-list/{projectId}/{periodId}")
    public ResponseObject updateIndexTodoList(@RequestBody MeUpdateTodoListRequest request,
                                              @DestinationVariable String projectId,
                                              @DestinationVariable String periodId) {
        return new ResponseObject(meTodoListService.updateIndexTodoList(request));
    }
}
