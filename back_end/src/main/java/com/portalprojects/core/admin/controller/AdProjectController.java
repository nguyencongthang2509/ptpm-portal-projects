package com.portalprojects.core.admin.controller;

import com.portalprojects.core.admin.service.AdProjectService;
import com.portalprojects.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author NguyenVinh
 */
@RestController
@RequestMapping("ad/project")
public class AdProjectController {

    @Autowired
    private AdProjectService adProjectService ;

    @GetMapping("/{page}")
    public ResponseEntity<?> fintAll (@PathVariable int page){
        Pageable pageResquest = PageRequest.of(page -1 ,5);
        List<Project> list = adProjectService.findAllProject(pageResquest);
        return ResponseEntity.ok(list);
    }
}
