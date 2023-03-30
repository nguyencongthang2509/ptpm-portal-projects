package com.portalprojects.core.admin.controller;

import com.portalprojects.core.admin.model.request.AdCearteMemberProjectRequest;
import com.portalprojects.core.admin.model.request.AdFindProjectRepuest;
import com.portalprojects.core.admin.model.request.AdUpdateMemberProjectRequest;
import com.portalprojects.core.admin.service.AdMemberProjectService;
import com.portalprojects.core.common.base.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NguyenVinh
 */
@RestController
@RequestMapping("/admin/member-project")
@CrossOrigin(origins = {"*"})
public class AdMemberProjectController {

    @Autowired
    private AdMemberProjectService adMemberProjectService;

    @GetMapping()
    public ResponseObject view() {
        return new ResponseObject(adMemberProjectService.getAll());
    }

    @GetMapping("/search")
    public ResponseObject search(final AdFindProjectRepuest rep) {
        return new ResponseObject(adMemberProjectService.searchProject(rep));
    }

    @GetMapping("/list-member-projetc/{id}")
    public ResponseObject findAllMemberJoinProject(@PathVariable("id") String idProject) {
        return new ResponseObject(adMemberProjectService.findAllMemberJoinProject(idProject));
    }

    @PostMapping
    public ResponseObject addMemberProject(@RequestBody AdCearteMemberProjectRequest cmd) {
        return new ResponseObject(adMemberProjectService.createMemberProject(cmd));
    }

    @PutMapping("/{id}")
    public ResponseObject updateMemberProjcet(@PathVariable("id") String id,
                                              @RequestBody AdUpdateMemberProjectRequest cmd) {
        cmd.setId(id);
        return new ResponseObject(adMemberProjectService.updateMemberProject(cmd));
    }

    @DeleteMapping("/{id}")
    public ResponseObject deleteMemberProject(@PathVariable("id") String id) {
        return new ResponseObject(adMemberProjectService.delete(id));
    }

    @GetMapping("/get-one")
    public ResponseObject getOne(@RequestParam("idProject") String idProject,
                                 @RequestParam("idMember") String idMember) {
        return new ResponseObject(adMemberProjectService.getOne(idMember, idProject));
    }
}
