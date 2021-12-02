package com.ruixin.controller;

import com.ruixin.bean.Role;
import com.ruixin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取所有角色
     */
    @PostMapping("/user/roles")
    @ResponseBody
    public List<Role> getRoles(){
        return roleService.findRole(null);
    }
}
