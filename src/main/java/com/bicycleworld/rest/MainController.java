package com.bicycleworld.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping(value="/message")
    public String getMessageOfTheDay() {
        return "Today is a lovely day!";
    }
}
