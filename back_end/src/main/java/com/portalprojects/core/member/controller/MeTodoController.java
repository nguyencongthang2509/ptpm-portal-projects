package com.portalprojects.core.member.controller;

import com.portalprojects.core.common.base.ResponseObject;
import com.portalprojects.core.member.model.response.MeTodoResponse;
import com.portalprojects.core.member.service.MeTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseObject getAllTodoByIdPeriodAndStatusTodo(@RequestParam("idPeriod") String idPeriod, @RequestParam("statusTodo") Integer statusTodo){
        return new ResponseObject(meTodoService.getToDoByPeriodAndPriorityLevel(idPeriod, statusTodo));
    }

    @GetMapping("/{id}")
    public ResponseObject findById(@PathVariable("id") String id){
        return new ResponseObject(meTodoService.findById(id));
    }

    @GetMapping("/detail/{id}")
    public ResponseObject getAllTodoByIdPeriodAndStatusTodo(@PathVariable("id") String id){
        return new ResponseObject(meTodoService.getDetailTodo(id));
    }
}
