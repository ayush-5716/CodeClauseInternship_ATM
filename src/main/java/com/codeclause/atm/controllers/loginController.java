package com.codeclause.atm.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codeclause.atm.dao.user_entRepository;
import com.codeclause.atm.entities.user_ent;
import com.codeclause.atm.intrmLoginObj.intrmUser;
import com.codeclause.atm.mappers.JsonHandler;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class loginController {

    public intrmUser userObj = new intrmUser();

    @Autowired
    user_entRepository userRepo;

    @GetMapping("/")
    public String login(Model model) {

        model.addAttribute("userObj", new user_ent());
        return "login";
    }

    @PostMapping("/post")
    public String saveMethod(Model model, user_ent userD, RedirectAttributes attr) throws JsonProcessingException {

        if (!userRepo.givePin(userD.getAcc_number()).isEmpty()
                && userRepo.givePin(userD.getAcc_number()).get(0).getPin() == userD.getPin()) {
            user_ent usObj = userRepo.givePin(userD.getAcc_number()).get(0);
            userD.setAmount(usObj.getAmount());
            System.out.println(userD.getPin());
            userD.setFirst_name(usObj.getFirst_name());
            userD.setLast_name(usObj.getLast_name());
            userD.setId(usObj.getId());
            model.addAttribute("usObj", userD);
            attr.addFlashAttribute("usb", usObj);
            JsonHandler jsH = new JsonHandler();
            jsH.write(userD);
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);
            model.addAttribute("date", strDate);
            return "redirect:/home/hm";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/create-account")
    public String createMethod(Model model){
        user_ent newUs = new user_ent();
        model.addAttribute("userObj", newUs);
        return "accCreate";
    }

    @PostMapping("/create-account/post")
    public String saveEntity(user_ent newUs){
        newUs.setAmount(0);
        userRepo.save(newUs);
        return "redirect:/";
    }

}
