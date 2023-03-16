package com.portalprojects.core.admin.controller;

import com.portalprojects.core.admin.model.request.AdCreateProjectRepuest;
import com.portalprojects.core.admin.model.request.AdFindProjectRepuest;
import com.portalprojects.core.admin.model.request.AdUpdateProjectRepuest;
import com.portalprojects.core.admin.model.response.AdProjectReponse;
import com.portalprojects.core.admin.service.AdProjectService;
import com.portalprojects.core.common.base.PageableObject;
import com.portalprojects.core.common.base.ResponseObject;
import com.portalprojects.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author NguyenVinh
 */
@RestController
@RequestMapping("/admin/project")
@CrossOrigin(origins = {"*"})
public class AdProjectController {

    @Autowired
    private AdProjectService adProjectService;

    @GetMapping("page/{page}")
    public ResponseEntity<?> fintAll(@PathVariable int page) {
        Pageable pageResquest = PageRequest.of(page - 1, 5);
        List<Project> list = adProjectService.findAllProject(pageResquest);
        return ResponseEntity.ok(list);
    }

    @GetMapping("")
    public ResponseObject viewProject(final AdFindProjectRepuest repuest) {
        return new ResponseObject((adProjectService.searchProject(repuest)));
    }

    @GetMapping("/{id}")
    public ResponseObject detailProject(@PathVariable("id") String id) {
        return new ResponseObject(adProjectService.findProjectById(id));
    }

    @GetMapping("/search")
    public ResponseObject searchProjce(final AdFindProjectRepuest repuest) {
        PageableObject<AdProjectReponse> listProjce = adProjectService.searchProject(repuest);
        return new ResponseObject(listProjce);
    }

    @PostMapping
    public ResponseObject addProject(@RequestBody AdCreateProjectRepuest cmd) {
        try {
            return new ResponseObject(adProjectService.createProject(cmd));
        } catch (Exception e) {
            return new ResponseObject(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseObject removeProject(@PathVariable("id") String id) {
        return new ResponseObject(adProjectService.removeProject(id));
    }

    @PutMapping("/{id}")
    public ResponseObject updateProjcet(@PathVariable("id") String id,
                                        @RequestBody AdUpdateProjectRepuest cmd) {
        cmd.setId(id);
        return new ResponseObject(adProjectService.updateProject(cmd));
    }
}
