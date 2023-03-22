package com.portalprojects.core.member.controller;

import com.portalprojects.core.common.base.ResponseObject;
import com.portalprojects.core.member.model.request.MeCreateOrDeleteAssignRequest;
import com.portalprojects.core.member.service.MeAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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

    @GetMapping
    public ResponseObject getMemberByIdTodo(@RequestParam("idTodo") String idTodo) {
        return new ResponseObject(meAssignService.getAllMemberByIdTodo(idTodo));
    }

    @MessageMapping("/create-assign")
    @SendTo("/portal-projects/assign")
    public ResponseObject create(@RequestBody MeCreateOrDeleteAssignRequest request) {
        return new ResponseObject(meAssignService.create(request));
    }

    @MessageMapping("/delete-assign")
    @SendTo("/portal-projects/assign")
    public ResponseObject delete(@RequestBody MeCreateOrDeleteAssignRequest request) {
        return new ResponseObject(meAssignService.delete(request));
    }
}
