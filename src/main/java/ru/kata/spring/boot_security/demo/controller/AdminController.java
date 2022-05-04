package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.AdminService;
import ru.kata.spring.boot_security.demo.service.RoleService;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    private RoleService roleService;


    @Autowired
    public AdminController(AdminService adminService, RoleService roleService) {
        this.adminService = adminService;
        this.roleService = roleService;
    }

    @RequestMapping("/admin")
    public String showAllUsers(Model model, Principal principal) {
        List<User> list = adminService.getAllUsers();

        model.addAttribute("attribute", list);
        model.addAttribute("userToAdd", new User());
        model.addAttribute("activeUser", adminService.loadUserByEmail(principal.getName()));
        return "adminInterface";
    }

    @PostMapping("/create")
    public String addUser(@ModelAttribute("userToAdd") User user,
                          @RequestParam(required = false) String roleToAdd) {

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRole("USER"));
        if (roleToAdd != null && roleToAdd.equals("ADMIN")) {
            roles.add(roleService.getRole("ADMIN"));
        }

        user.setRoles(roles);
        adminService.add(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/update", method={RequestMethod.PUT, RequestMethod.GET})
    public String editUser(@ModelAttribute("user") User user,
                           @RequestParam(required = false) String roleAdmin) {

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRole("USER"));
        if (roleAdmin != null && roleAdmin.equals("ADMIN")) {
            roles.add(roleService.getRole("ADMIN"));
        }
        user.setRoles(roles);
        adminService.set(user);
        return "redirect:/admin";
    }

    @RequestMapping("/delete")
    public String delete(User user) {
        adminService.delete(user.getId());
        return "redirect:/admin";
    }

    @RequestMapping("/myInfo")
    public String showUserInfo(Principal principal, Model model) {
        model.addAttribute("activeUser", adminService.loadUserByEmail(principal.getName()));

        return "userInterface";
    }

    @RequestMapping("/getOne")
    @ResponseBody
    public Optional<User> getOne(Long id)
    {
        return adminService.get(id);
    }
}
