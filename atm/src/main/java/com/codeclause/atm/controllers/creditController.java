package com.codeclause.atm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codeclause.atm.dto.loggedUserDetails;

@RequestMapping(method=RequestMethod.GET,value="/credit")
@Controller
public class creditController {

    @Autowired
    loggedUserDetails lgd;

    

    @GetMapping("/cr")
    public String creditPage(Model model){
        return "credit";
    }

    @GetMapping("/crToBank")
    public String creditToBank(Model model){
        return "credit";
    }
}
