package com.codeclause.atm.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codeclause.atm.entities.user_ent;
import com.codeclause.atm.mappers.JsonHandler;

@Controller
public class withdrawlController {
    
    @GetMapping("/withdrawl")
    public String with(Model model) throws IOException{
        user_ent us = new JsonHandler().getObject();
        model.addAttribute("usData", us);
        return "withdrawl";
    }
}
