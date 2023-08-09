package com.codeclause.atm.mappers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.codeclause.atm.entities.user_ent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHandler {

    public void write(user_ent userD) throws JsonProcessingException {
        ObjectMapper objMap = new ObjectMapper();
        String data = objMap.writeValueAsString(userD);
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File("src/main/resources/templates/dataStore.json"));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public user_ent getObject() throws IOException{
        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/templates/dataStore.json"));

        ObjectMapper objectMap =  new ObjectMapper();
        user_ent us = objectMap.readValue(jsonData,user_ent.class);
        return us;  
    }
}
