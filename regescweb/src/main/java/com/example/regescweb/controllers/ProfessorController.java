package com.example.regescweb.controllers;

import com.example.regescweb.models.Professor;
import com.example.regescweb.models.StatusProfessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProfessorController {
    @GetMapping("/professores")
    public ModelAndView index()
    {
        Professor kamala = new Professor("Kamala", new BigDecimal(5000.0), StatusProfessor.ATIVO);
        kamala.setId(1L);
        Professor lockjaw = new Professor("Lockjaw", new BigDecimal(10000.0), StatusProfessor.APOSENTADO);
        lockjaw.setId(2L);
        Professor medusa = new Professor("Medusa", new BigDecimal(15000.0), StatusProfessor.INATIVO);
        medusa.setId(3L);
        List<Professor> professores = Arrays.asList(kamala, lockjaw, medusa);

        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);

        return mv;
    }
}
