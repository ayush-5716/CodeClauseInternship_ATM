package com.codeclause.atm.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codeclause.atm.dao.user_entRepository;
import com.codeclause.atm.entities.user_ent;
import com.codeclause.atm.intrmObj.intrmUser;
import com.codeclause.atm.intrmObj.weatherObj;
import com.codeclause.atm.mappers.JsonHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

@Controller
public class loginController {

    public intrmUser userObj = new intrmUser();
    String temp = "";
    String windSpeed = "";

    weatherObj weathObj = new weatherObj();
    JsonHandler<weatherObj> jsonWeath = new JsonHandler<>();

    @Autowired
    user_entRepository userRepo;

    static class DataPayload {
        private String longitude;
        private String latitude;

        public String getLongitude() {
            return longitude;
        }

        public String getLatitude() {
            return latitude;
        }

    }

    @GetMapping("/")
    public String login(Model model) throws IOException {
        // model.addAttribute("weath", jsonWeath.getWeatherObject());
        model.addAttribute("userObj", new user_ent());
        return "login";
    }

    @GetMapping("/logout")
    public String logOut(Model model) {
        jsonWeath.deleteData("dataStore.json");
        jsonWeath.deleteData("weathStore.json");
        return "redirect:/";
    }

    @PostMapping("/send-data")
    public void dataRecv(@RequestBody DataPayload payload) throws IOException {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + payload.getLatitude() + "&longitude="
                + payload.getLongitude()
                + "&current_weather=true";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
        JsonElement weather = jsonObject.get("current_weather");
        JsonObject jsonObject1 = gson.fromJson(weather, JsonObject.class);
        this.temp = jsonObject1.get("temperature").getAsString();
        this.windSpeed = jsonObject1.get("windspeed").getAsString();
        weathObj.setTemp(temp);
        weathObj.setWindSpeed(windSpeed);
        jsonWeath.write(weathObj, "weathStore.json");

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
            JsonHandler<user_ent> jsH = new JsonHandler<user_ent>();
            jsH.write(userD, "dataStore.json");
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
    public String createMethod(Model model) {
        user_ent newUs = new user_ent();
        model.addAttribute("userObj", newUs);
        return "accCreate";
    }

    @PostMapping("/create-account/post")
    public String saveEntity(user_ent newUs) {
        newUs.setAmount(0);
        userRepo.save(newUs);
        return "redirect:/";
    }

}
