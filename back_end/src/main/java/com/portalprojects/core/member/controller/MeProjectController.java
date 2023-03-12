package com.portalprojects.core.member.controller;

import com.portalprojects.core.common.base.ResponseObject;
import com.portalprojects.core.member.model.request.MeFindProjectRequest;
import com.portalprojects.core.member.service.MeProjectService;
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
@RequestMapping("/member")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class MeProjectController {

    private String idUser = "c5cf1e20-bdd4-11ed-afa1-0242ac120002";

    @Autowired
    private MeProjectService meProjectService;

    @GetMapping
    public ResponseObject getProjectByIdUser(final MeFindProjectRequest request){
        return new ResponseObject(meProjectService.getAllProjectByIdUser(request, idUser));
    }

    @GetMapping("/detail-project/{id}")
    public ResponseObject detailProject(@PathVariable("id") String id){
        System.out.println(id);
        return new ResponseObject(meProjectService.findById(id));
    }
}
