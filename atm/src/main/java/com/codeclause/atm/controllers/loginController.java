package com.codeclause.atm.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeclause.atm.dao.user_entRepository;
import com.codeclause.atm.entities.user_ent;
import com.codeclause.atm.intrmLoginObj.intrmUser;

@Controller
public class loginController {
    
    public user_ent usObj;
    public intrmUser userObj = new intrmUser();
    
    public user_ent getUsObj() {
        return usObj;
    }

    public void setUsObj(user_ent usObj) {
        this.usObj = usObj;
    }

    @Autowired
    user_entRepository userRepo;

    @GetMapping("/")
    public String login(Model model){
        
        model.addAttribute("userObj", userObj);
        return "login";
    }

    @PostMapping("/post")
    public String saveMethod(Model model,@RequestParam(value="account-number",required=true) Long accNumber,@RequestParam(value="pin",required=true) Long pin){
        usObj = userRepo.givePin(accNumber).get(0);
        if(!userRepo.givePin(accNumber).isEmpty() && userRepo.givePin(accNumber).get(0).getPin() == pin){
            model.addAttribute("usoBj",usObj);
            return "home";
        }
        else{
            return "login";
        }
    }

}
