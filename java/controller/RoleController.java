package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pojo.Role;
import service.RoleService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RequestMapping(value = "/role")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{roleId}")
    public String delete(@PathVariable("roleId")long roleId){
        roleService.delete(roleId);
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/list";
    }


}
