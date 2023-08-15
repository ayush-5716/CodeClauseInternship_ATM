package com.codeclause.atm.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codeclause.atm.entities.user_ent;
import com.codeclause.atm.intrmObj.weatherObj;
import com.codeclause.atm.mappers.JsonHandler;

@RequestMapping(method = RequestMethod.GET,value = "/home")
@Controller
public class homeController {

    JsonHandler<weatherObj> js = new JsonHandler<>();
    
    @GetMapping("/hm")
    public String home(user_ent usb,RedirectAttributes attr,Model model) throws IOException{
        user_ent us = new JsonHandler<user_ent>().getObject();
        model.addAttribute("usData", us);
        model.addAttribute("weath", js.getWeatherObject());
        // attr.addFlashAttribute("usb", usb);
        return "home2";
    }
}