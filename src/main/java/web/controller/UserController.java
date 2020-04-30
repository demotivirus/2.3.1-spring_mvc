package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView allUsers() {
        List<User> users = userService.listUsers();
        ModelAndView mv = new ModelAndView("listOfUsers");
        mv.addObject("usersList", users);
        return mv;
    }

    @GetMapping("/addUser")
    public ModelAndView addUserGet() {
        ModelAndView mv = new ModelAndView("editUser");
        return mv;
    }

    @PostMapping("/addUser")
    public ModelAndView addUserPost(@ModelAttribute("user") User user) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");
        userService.add(user);
        return mv;
    }

    @GetMapping("/editUser")
    public ModelAndView editUserGet(@RequestParam(required = true)Long id) {
        ModelAndView mv = new ModelAndView();

        User user = userService.getUserFromId(id);
        if (user != null) {
            mv.setViewName("editUser");
            mv.addObject("user", user);
            return mv;
        }
        else{
            mv.setViewName("redirect:/");
            return mv;
        }
    }

    @PostMapping("/editUser")
    public ModelAndView editUserPost(@ModelAttribute("user") User user) {
        ModelAndView mv = new ModelAndView();
        userService.edit(user);
        mv.setViewName("redirect:/");
        return mv;
    }

    @GetMapping("/deleteUser/{id}")
    public ModelAndView deleteUserGet(@PathVariable("id") Long id) {
        User user = userService.getUserFromId(id);
        userService.delete(user);
        ModelAndView mv = new ModelAndView("redirect:/");
        return mv;
    }
}
