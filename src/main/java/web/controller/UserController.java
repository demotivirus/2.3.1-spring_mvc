package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView allUsers(){
        List<User> users = userService.listUsers();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("listOfUsers");
        mv.addObject("usersList", users);
        return mv;
    }

    @GetMapping("/addUser")
    public ModelAndView addUserGet(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("editUser");
        return mv;
    }

    @PostMapping("/addUser")
    public ModelAndView addUserPost(@ModelAttribute("user")User user){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");
        userService.add(user);
        return mv;
    }

    @GetMapping("/editUser/{id}")
    public ModelAndView editUserGet(@PathVariable("id")long id){
        User user = userService.getUserFromId(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("editUser");
        mv.addObject("user", user);
        return mv;
    }

    @PostMapping("/editUser")
    public ModelAndView editUserPost(@ModelAttribute("user")User user){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");
        userService.edit(user);
        return mv;
    }

    @GetMapping("/deleteUser/{id}")
    public ModelAndView deleteUserGet(@PathVariable("id") long id){
        User user = userService.getUserFromId(id);
        userService.delete(user);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");
        return mv;
    }
}
