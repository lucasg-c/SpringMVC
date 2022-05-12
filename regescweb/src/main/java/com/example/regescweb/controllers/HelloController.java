package com.example.regescweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public ModelAndView hello()
    {
        System.out.println("Testando mensagem no console do Servidor.");
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("nome", "Model&View");
        return mv;
    }

    @GetMapping("/hello-model")
    public String hello(Model model)
    {
        System.out.println("Testando mensagem no console do Servidor.");
        model.addAttribute("nome", "Model");
        return "hello.html";
    }

    @GetMapping("/hello-servlet")
    public String hello(HttpServletRequest request)
    {
        System.out.println("Testando mensagem no console do Servidor.");
        request.setAttribute("nome", "Lucas");
        return "hello.html";
    }
}
