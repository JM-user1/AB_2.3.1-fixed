package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImp;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm started page!");
        model.addAttribute("messages", messages);
        return "index";
    }

    @GetMapping(value = "/all-users")
    public String AllUsers(ModelMap model) {
        model.addAttribute("users",userService.getAllUsers());
        return "all-users";
    }

    @GetMapping("/add-user")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }


    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/all-users";
    }

    @GetMapping("/edit")
    public ModelAndView editUser(@RequestParam(name = "id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit-users");
        User user = userService.getById(id);
        modelAndView.addObject(user);
        return modelAndView;
    }

    @PostMapping(value = "/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.edit(user);
        return "redirect:/all-users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(name = "id") long id) {
        userService.delete(id);
        return "redirect:/all-users";
    }
}
