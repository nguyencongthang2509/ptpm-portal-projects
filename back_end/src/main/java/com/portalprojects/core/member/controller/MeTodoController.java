package com.portalprojects.core.member.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thangncph26123
 */
@RestController
@RequestMapping("/member/todo")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class MeTodoController {

}
