package com.portalprojects.core.member.controller;

import com.portalprojects.core.common.base.ResponseObject;
import com.portalprojects.core.member.service.MeLabelTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping
    public ResponseObject create(@RequestParam("idLabel") String idlabel, @RequestParam("idTodo") String idTodo){
        return new ResponseObject(meLabelTodoService.create(idlabel, idTodo));
    }

    @DeleteMapping
    public ResponseObject delete(@RequestParam("idLabel") String idlabel, @RequestParam("idTodo") String idTodo){
        return new ResponseObject(meLabelTodoService.delete(idlabel, idTodo));
    }
}
