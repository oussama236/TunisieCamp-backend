package com.gladiators.pi_spring.Controllers;


import org.springframework.web.bind.annotation.*;

@RestController


public class HelloController {


    @PostMapping("/hello")
    @ResponseBody
    public String hello2(@RequestBody String name){
        String X ="Hello"+" "+name;
        return X;
    }





}