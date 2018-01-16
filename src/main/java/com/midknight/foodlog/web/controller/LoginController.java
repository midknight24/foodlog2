package com.midknight.foodlog.web.controller;

import com.midknight.foodlog.model.Role;
import com.midknight.foodlog.model.User;
import com.midknight.foodlog.service.RoleServiceImpl;
import com.midknight.foodlog.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    public UserServiceImpl userService;

    @Autowired
    public RoleServiceImpl roleService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginForm(Model model, HttpServletRequest request) {
        model.addAttribute("user", new User());
        try {
            Object flash = request.getSession().getAttribute("flash");
            model.addAttribute("flash", flash);

            request.getSession().removeAttribute("flash");
        } catch (Exception ex) {
            // "flash" session attribute must not exist...do nothing and proceed normally
        }
        return "login";
    }

    @RequestMapping(path = "/sign_up", method = RequestMethod.GET)
    public String signupForm(Model model){
        User user = new User();

        model.addAttribute("user",user);
        model.addAttribute("activate",true);
        return "sign_up";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(User user){
        Role role = roleService.findById(1);
        user.setRole(role);
        user.setEnabled(true);
        userService.save(user);
        return "login";
    }

    @RequestMapping("/access_denied")
    public String accessDenied() {
        return "access_denied";
    }



}