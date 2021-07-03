package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pojo.Role;
import pojo.User;
import service.RoleService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> userList = userService.list();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping(value = "/saveUI")
    public ModelAndView saveUI(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("user-add");
        return modelAndView;
    }

    @RequestMapping(value = "/save")
    public String save(User user, long[] roleIds){
        for (long roleId : roleIds) {
            System.out.println(roleId);
        }
        userService.save(user, roleIds);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/delete/{userId}")
    public String delete(@PathVariable("userId")long userId){
        userService.delete(userId);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(String username, String password, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(username + ":" + password);
        boolean flag = userService.login(username,password);
        if(flag){
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            modelAndView.setViewName("main");
        }else {
            modelAndView.addObject("flag", true);
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

}
