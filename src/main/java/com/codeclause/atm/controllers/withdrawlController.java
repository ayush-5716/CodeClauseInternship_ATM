package com.codeclause.atm.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeclause.atm.dao.user_entRepository;
import com.codeclause.atm.entities.user_ent;
import com.codeclause.atm.intrmObj.weatherObj;
import com.codeclause.atm.mappers.JsonHandler;

@Controller
public class withdrawlController {
    JsonHandler<user_ent> jsH = new JsonHandler<user_ent>();
    JsonHandler<weatherObj> js = new JsonHandler<>();
    @Autowired
    user_entRepository usRepo;
    
    @GetMapping("/withdrawl")
    public String with(Model model) throws IOException{
        user_ent us = new JsonHandler<user_ent>().getObject();
        model.addAttribute("usData", us);
        model.addAttribute("weath", js.getWeatherObject());
        return "withdrawl";
    }

    @PostMapping("/withFromBank")
    public String creditToBank(Model model,@RequestParam(value="amount") Long amount,@RequestParam(value="pin") Long pin) throws IOException{
        user_ent usObj = jsH.getObject();
        if(usObj.getPin() == pin){
        usRepo.loseAmount(usObj.getAmount() - amount,usObj.getAcc_number());
        usObj.setAmount(usObj.getAmount() - amount );
        jsH.write(usObj,"dataStore.json");
        }
        return "redirect:/credit/cr";
    }
}
