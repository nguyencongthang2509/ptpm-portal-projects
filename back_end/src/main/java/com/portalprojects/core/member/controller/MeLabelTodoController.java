package com.portalprojects.core.member.controller;

import com.portalprojects.core.common.base.ResponseObject;
import com.portalprojects.core.member.model.request.DesVarProjectIdAndPeriodIdRequest;
import com.portalprojects.core.member.model.request.MeCreateOrDeleteAssignRequest;
import com.portalprojects.core.member.model.request.MeCreateOrDeleteLabelTodoRequest;
import com.portalprojects.core.member.service.MeLabelTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thangncph26123
 */
@RestController
@RequestMapping("/member/label-todo")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class MeLabelTodoController {

    @Autowired
    private MeLabelTodoService meLabelTodoService;

    @MessageMapping("/create-label-todo/{projectId}/{periodId}")
    @SendTo("/portal-projects/label-todo/{projectId}/{periodId}")
    public ResponseObject create(@RequestBody MeCreateOrDeleteLabelTodoRequest request,
                                 @ModelAttribute DesVarProjectIdAndPeriodIdRequest des) {
        return new ResponseObject(meLabelTodoService.create(request));
    }

    @MessageMapping("/delete-label-todo/{projectId}/{periodId}")
    @SendTo("/portal-projects/label-todo/{projectId}/{periodId}")
    public ResponseObject delete(@RequestBody MeCreateOrDeleteLabelTodoRequest request,
                                 @ModelAttribute DesVarProjectIdAndPeriodIdRequest des) {
        return new ResponseObject(meLabelTodoService.delete(request));
    }
}
