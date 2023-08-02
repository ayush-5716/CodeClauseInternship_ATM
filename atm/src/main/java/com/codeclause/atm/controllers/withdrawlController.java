package com.codeclause.atm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class withdrawlController {
    
    @GetMapping("/withdrawl")
    public String with(Model model){
        return "withdrawl";
    }
}
