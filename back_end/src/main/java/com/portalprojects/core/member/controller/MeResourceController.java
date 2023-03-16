package com.portalprojects.core.member.controller;

import com.portalprojects.core.common.base.ResponseObject;
import com.portalprojects.core.member.model.request.MeFindResourceRequest;
import com.portalprojects.core.member.service.MeResourceService;
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
@RequestMapping("/member/resource")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class MeResourceController {

    @Autowired
    private MeResourceService meResourceService;

    @GetMapping("/{id}")
    public ResponseObject getAllResourceByIdProject(final MeFindResourceRequest request, @PathVariable("id") String id) {
        return new ResponseObject(meResourceService.getAllResourceByIdProject(request, id));
    }
}
