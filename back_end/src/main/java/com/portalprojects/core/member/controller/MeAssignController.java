package com.portalprojects.core.member.controller;

import com.portalprojects.core.common.base.ResponseObject;
import com.portalprojects.core.member.model.request.MeCreateOrDeleteAssignRequest;
import com.portalprojects.core.member.service.MeAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thangncph26123
 */
@RestController
@RequestMapping("/member/assign")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class MeAssignController {

    @Autowired
    private MeAssignService meAssignService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping
    public ResponseObject getMemberByIdTodo(@RequestParam("idTodo") String idTodo) {
        return new ResponseObject(meAssignService.getAllMemberByIdTodo(idTodo));
    }

    @MessageMapping("/create-assign/{projectId}/{periodId}")
    @SendTo("/portal-projects/assign/{projectId}/{periodId}")
    public ResponseObject create(@RequestBody MeCreateOrDeleteAssignRequest request,
                                 @DestinationVariable String projectId,
                                 @DestinationVariable String periodId) {
        return new ResponseObject(meAssignService.create(request));
    }


    @MessageMapping("/delete-assign/{projectId}/{periodId}")
    @SendTo("/portal-projects/assign/{projectId}/{periodId}")
    public ResponseObject delete(@RequestBody MeCreateOrDeleteAssignRequest request,
                                 @DestinationVariable String projectId,
                                 @DestinationVariable String periodId) {
        return new ResponseObject(meAssignService.delete(request));
    }
}
