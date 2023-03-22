package com.portalprojects.core.member.controller;

import com.portalprojects.core.common.base.ResponseObject;
import com.portalprojects.core.member.service.MeAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping
    public ResponseObject create(@RequestParam("idMember") String idMember, @RequestParam("idTodo") String idTodo){
        return new ResponseObject(meAssignService.create(idMember, idTodo));
    }

    @DeleteMapping
    public ResponseObject delete(@RequestParam("idMember") String idMember, @RequestParam("idTodo") String idTodo){
        return new ResponseObject(meAssignService.delete(idMember, idTodo));
    }
}
