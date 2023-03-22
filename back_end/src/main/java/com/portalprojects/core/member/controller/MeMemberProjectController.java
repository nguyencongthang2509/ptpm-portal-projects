package com.portalprojects.core.member.controller;

import com.portalprojects.core.common.base.ResponseObject;
import com.portalprojects.core.member.service.MeMemberProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thangncph26123
 */
@RestController
@RequestMapping("/member/member-project")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class MeMemberProjectController {

    @Autowired
    private MeMemberProjectService meMemberProjectService;

    @GetMapping("/{id}")
    public ResponseObject getAllMemberProject(@PathVariable("id") String id) {
        return new ResponseObject(meMemberProjectService.getAllMemberProject(id));
    }

}
