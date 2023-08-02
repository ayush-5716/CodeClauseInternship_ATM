package com.codeclause.atm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(method = RequestMethod.GET,value = "/home")
@Controller
public class homeController {
    
    @GetMapping("/hm")
    public String home(Model model){
        return "home";
    }
}
