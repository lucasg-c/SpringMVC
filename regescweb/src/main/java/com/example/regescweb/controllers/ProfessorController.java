package com.example.regescweb.controllers;

import com.example.regescweb.dto.RequisicaoNovoProfessor;
import com.example.regescweb.models.Professor;
import com.example.regescweb.models.StatusProfessor;
import com.example.regescweb.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/professores")
    public ModelAndView index()
    {
        List<Professor> professores = this.professorRepository.findAll();

        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);

        return mv;
    }

    @GetMapping("/professores/new")
    public ModelAndView nnew()
    {
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("statusProfessor", StatusProfessor.values());
        return mv;
    }

    @PostMapping("/professores")
    public String create(RequisicaoNovoProfessor requisicao)
    {
        Professor professor = requisicao.toProfessor();
        System.out.println("Testando redirect no terminal.");
        System.out.println();
        System.out.println(requisicao);
        System.out.println();
        System.out.println(professor);
        System.out.println();
        this.professorRepository.save(professor);

        return "redirect:/professores";
    }
}
