package com.example.regescweb.controllers;

import com.example.regescweb.dto.RequisicaoNovoProfessor;
import com.example.regescweb.models.Professor;
import com.example.regescweb.models.StatusProfessor;
import com.example.regescweb.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    public String create(@Valid RequisicaoNovoProfessor requisicao, BindingResult bindingResult)
    {
        System.out.println(requisicao);
        if (bindingResult.hasErrors())
        {
            System.out.println("\n=-=-=-Erros no formulário=-=-=-=\n");
            return "redirect:/professores/new";
        }
        else
        {
            System.out.println("\n=-=-=-Formulário passou.=-=-=-\n");
            Professor professor = requisicao.toProfessor();
            this.professorRepository.save(professor);
        }

        return "redirect:/professores";
    }
}
