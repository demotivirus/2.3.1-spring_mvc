package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestPage {

    @GetMapping("test")
    public String helloWorld(){
        return "test";
    }
}
