package com.example.regescweb.controllers;

import com.example.regescweb.dto.RequisicaoFormProfessor;
import com.example.regescweb.models.Professor;
import com.example.regescweb.models.StatusProfessor;
import com.example.regescweb.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/professores")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("")
    public ModelAndView index()
    {
        List<Professor> professores = this.professorRepository.findAll();

        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormProfessor requisicaoNovoProfessor)
    {
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("listaStatusProfessor", StatusProfessor.values());
        return mv;
    }

    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormProfessor requisicao, BindingResult bindingResult)
    {
        System.out.println(requisicao);
        if (bindingResult.hasErrors())
        {
            System.out.println("\n=-=-=-Erros no formulário=-=-=-=\n");
            ModelAndView mv = new ModelAndView("professores/new");
            mv.addObject("listaStatusProfessor", StatusProfessor.values());
            return mv;
        }
        else
        {
            System.out.println("\n=-=-=-Formulário passou.=-=-=-\n");
            Professor professor = requisicao.toProfessor();
            this.professorRepository.save(professor);

            return new ModelAndView("redirect:/professores/" + professor.getId());
        }
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id)
    {
        System.out.println("=-=-=-=-= ID: " + id + " =-=-=-=-");
        Optional<Professor> optional = this.professorRepository.findById(id);
        if (optional.isPresent())
        {
            Professor professor = optional.get();
            ModelAndView mv = new ModelAndView("professores/show");
            mv.addObject("professor", professor);

            return mv;
        }
        else
        {
            System.out.println("=-=-=-=Não achou o objeto de ID " + id + " .=-=-=-=");
            return new ModelAndView("redirect:/professores");
        }
//        ModelAndView mv = new ModelAndView("professores/show");
//
//        return mv;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RequisicaoFormProfessor requisicao)
    {
        Optional<Professor> optional = this.professorRepository.findById(id);
        if (optional.isPresent())
        {
            Professor professor = optional.get();
            requisicao.fromProfessor(professor);
            ModelAndView mv = new ModelAndView("professores/edit");
            mv.addObject("professorId", professor.getId());
            mv.addObject("listaStatusProfessor", StatusProfessor.values());

            return mv;
        }
        else
        {
            System.out.println("=-=-=-=Não achou o objeto de ID " + id + " .=-=-=-=");
            return new ModelAndView("redirect:/professores");
        }
    }
}
