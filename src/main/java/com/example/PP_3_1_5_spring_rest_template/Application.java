package com.example.PP_3_1_5_spring_rest_template;

import com.example.PP_3_1_5_spring_rest_template.controller.RestController;
import com.example.PP_3_1_5_spring_rest_template.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(Application.class, args);

        RestController restController = context.getBean("restController",
                RestController.class);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<String> cookies = restController.getAllUsers(new HttpEntity<>(httpHeaders));
        System.out.println(cookies);

        httpHeaders.set("Cookie", String.join(";", cookies));

        User newUser = new User(3L, "James", "Brown", (byte) 30);

        HttpEntity<User> httpEntity = new HttpEntity<>(newUser, httpHeaders);

        restController.addUser(httpEntity);

        newUser.setName("Thomas");
        newUser.setLastName("Shelby");

        restController.editUser(httpEntity);

        restController.deleteUser(httpEntity, 3);

        System.out.println(restController.getCode());
    }
}