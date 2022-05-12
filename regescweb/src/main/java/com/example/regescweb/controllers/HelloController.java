package com.example.regescweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello()
    {
        System.out.println("Testando mensagem no console do Servidor.");
        return "hello.html";
    }
}
