package com.codeclause.atm.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codeclause.atm.dao.user_entRepository;
import com.codeclause.atm.entities.user_ent;
import com.codeclause.atm.intrmObj.weatherObj;
import com.codeclause.atm.mappers.JsonHandler;



@RequestMapping(method=RequestMethod.GET,value="/credit")
@Controller
public class creditController {

    JsonHandler<user_ent> jsH = new JsonHandler<user_ent>();
    JsonHandler<weatherObj> js = new JsonHandler<>();
    @Autowired
    user_entRepository usRepo;
    @GetMapping("/cr")
    public String creditPage(Model model,user_ent usb,RedirectAttributes attr) throws IOException{
        model.addAttribute("usb", usb);
        // attr.addFlashAttribute("usb", usb);
        user_ent us = new JsonHandler<user_ent>().getObject();
        model.addAttribute("usData", us);
        model.addAttribute("weath", js.getWeatherObject());
        return "credit";
    }

    @PostMapping("/crToBank")
    public String creditToBank(Model model,@RequestParam(value="amount") Long amount,@RequestParam(value="pin") Long pin) throws IOException{
        user_ent usObj = jsH.getObject();
        if(usObj.getPin() == pin){
        usRepo.updateAmount(usObj.getAmount() + amount,usObj.getAcc_number());
        usObj.setAmount(usObj.getAmount() + amount );
        jsH.write(usObj,"dataStore.json");
        }
        return "redirect:/credit/cr";
    }
}
