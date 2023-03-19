package com.portalprojects.core.member.controller;

import com.portalprojects.core.common.base.ResponseObject;
import com.portalprojects.core.member.service.MeLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thangncph26123
 */
@RestController
@RequestMapping("/member/label")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class MeLabelController {

    @Autowired
    private MeLabelService meLabelService;

    @GetMapping
    public ResponseObject getAllLabelByIdTodo(@RequestParam("idTodo") String idTodo) {
        return new ResponseObject(meLabelService.getAllLabelByIdTodo(idTodo));
    }
}
