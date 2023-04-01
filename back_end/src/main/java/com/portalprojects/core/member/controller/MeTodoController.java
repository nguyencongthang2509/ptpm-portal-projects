package com.portalprojects.core.member.controller;

import com.portalprojects.core.common.base.ResponseObject;
import com.portalprojects.core.member.model.request.DesVarProjectIdAndPeriodIdRequest;
import com.portalprojects.core.member.model.request.MeCreateDetailTodoRequest;
import com.portalprojects.core.member.model.request.MeDeleteDeadlineTodoRequest;
import com.portalprojects.core.member.model.request.MeDeleteDetailTodoRequest;
import com.portalprojects.core.member.model.request.MeUpdateDeTailTodoRequest;
import com.portalprojects.core.member.model.request.MeUpdateDeadlineTodoRequest;
import com.portalprojects.core.member.model.request.MeUpdateDescriptionsTodoRequest;
import com.portalprojects.core.member.model.request.MeUpdateStatusTodoRequest;
import com.portalprojects.core.member.model.request.MeUpdateTodoRequest;
import com.portalprojects.core.member.service.MeTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thangncph26123
 */
@RestController
@RequestMapping("/member/todo")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class MeTodoController {

    @Autowired
    private MeTodoService meTodoService;

    @GetMapping
    public ResponseObject getAllTodoByIdPeriodAndStatusTodo(@RequestParam("idPeriod") String idPeriod,
                                                            @RequestParam("idTodoList") String idTodoList) {
        return new ResponseObject(meTodoService.getToDoByPeriodAndPriorityLevel(idPeriod, idTodoList));
    }

    @GetMapping("/{id}")
    public ResponseObject findById(@PathVariable("id") String id) {
        return new ResponseObject(meTodoService.findById(id));
    }

    @GetMapping("/detail/{id}")
    public ResponseObject getAllTodoByIdPeriodAndStatusTodo(@PathVariable("id") String id) {
        return new ResponseObject(meTodoService.getDetailTodo(id));
    }

    @MessageMapping("/update-priority-todo/{projectId}/{periodId}")
    @SendTo("/portal-projects/todo/{projectId}/{periodId}")
    public ResponseObject updatePriorityLevel(@RequestBody MeUpdateTodoRequest request,
                                              @ModelAttribute DesVarProjectIdAndPeriodIdRequest des,
                                              StompHeaderAccessor headerAccessor) {
        return new ResponseObject(meTodoService.updatePriorityLevel(request, headerAccessor));
    }

    @MessageMapping("/create-todo-checklist/{projectId}/{periodId}")
    @SendTo("/portal-projects/create-todo-checklist/{projectId}/{periodId}")
    public ResponseObject createTodoChecklist(@RequestBody MeCreateDetailTodoRequest request,
                                              @ModelAttribute DesVarProjectIdAndPeriodIdRequest des,
                                              StompHeaderAccessor headerAccessor) {
        return new ResponseObject(meTodoService.createTodoChecklist(request, headerAccessor));
    }

    @MessageMapping("/update-todo-checklist/{projectId}/{periodId}")
    @SendTo("/portal-projects/update-todo-checklist/{projectId}/{periodId}")
    public ResponseObject updateTodoChecklist(@RequestBody MeUpdateDeTailTodoRequest request,
                                              @ModelAttribute DesVarProjectIdAndPeriodIdRequest des,
                                              StompHeaderAccessor headerAccessor) {
        return new ResponseObject(meTodoService.updateTodoChecklist(request, headerAccessor));
    }

    @MessageMapping("/update-statustodo-todo-checklist/{projectId}/{periodId}")
    @SendTo("/portal-projects/update-statustodo-todo-checklist/{projectId}/{periodId}")
    public ResponseObject updateStatusTodoTodoChecklist(@RequestBody MeUpdateStatusTodoRequest request,
                                                        @ModelAttribute DesVarProjectIdAndPeriodIdRequest des,
                                                        StompHeaderAccessor headerAccessor) {
        return new ResponseObject(meTodoService.updateStatusTodo(request, headerAccessor));
    }

    @MessageMapping("/delete-todo-checklist/{projectId}/{periodId}")
    @SendTo("/portal-projects/delete-todo-checklist/{projectId}/{periodId}")
    public ResponseObject deleteTodoChecklist(@RequestBody MeDeleteDetailTodoRequest request,
                                              @ModelAttribute DesVarProjectIdAndPeriodIdRequest des,
                                              StompHeaderAccessor headerAccessor) {
        return new ResponseObject(meTodoService.deleteDetailTodo(request, headerAccessor));
    }

    @MessageMapping("/update-descriptions-todo/{projectId}/{periodId}")
    @SendTo("/portal-projects/update-descriptions-todo/{projectId}/{periodId}")
    public ResponseObject updateDescriptionsTodo(@RequestBody MeUpdateDescriptionsTodoRequest request,
                                                 @ModelAttribute DesVarProjectIdAndPeriodIdRequest des,
                                                 StompHeaderAccessor headerAccessor
    ) {
        return new ResponseObject(meTodoService.updateDescriptionsTodo(request, headerAccessor));
    }

    @MessageMapping("/update-deadline-todo/{projectId}/{periodId}")
    @SendTo("/portal-projects/update-deadline-todo/{projectId}/{periodId}")
    public ResponseObject updateDeadlineTodo(@RequestBody MeUpdateDeadlineTodoRequest request,
                                             @ModelAttribute DesVarProjectIdAndPeriodIdRequest des,
                                             StompHeaderAccessor headerAccessor) {
        return new ResponseObject(meTodoService.updateDeadlineTodo(request, headerAccessor));
    }

    @MessageMapping("/delete-deadline-todo/{projectId}/{periodId}")
    @SendTo("/portal-projects/delete-deadline-todo/{projectId}/{periodId}")
    public ResponseObject deleteDeadlineTodo(@RequestBody MeDeleteDeadlineTodoRequest request,
                                             @ModelAttribute DesVarProjectIdAndPeriodIdRequest des,
                                             StompHeaderAccessor headerAccessor) {
        return new ResponseObject(meTodoService.deleteDeadlineTodo(request, headerAccessor));
    }

}
