package br.com.springmvc.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.springmvc.models.Tarefa;

@Controller
public class TarefasController {

	List<Tarefa> tarefas = new ArrayList<Tarefa>();

	@GetMapping("/create")
	public ModelAndView home(Tarefa tarefa) {
		ModelAndView mv = new ModelAndView("create");

		return mv;
	}

	@PostMapping("/create")
	public ModelAndView create(@Valid Tarefa t, BindingResult result) {
		if(result.hasErrors()) {
			return home(t);
		}
		
		ModelAndView mv = new ModelAndView("redirect:/mostrar-tarefas");

		Long id = tarefas.size() + 1l;
		
		if (t.getId() == null) {
			Tarefa tarefa = new Tarefa(id, t.getTarefa(), t.getData(), t.getHora());
			tarefas.add(tarefa);
		} else {
			for (Tarefa tarefa : tarefas) {
				if (tarefa.getId() == t.getId()) {
					int index = tarefas.indexOf(tarefa);
					tarefas.set(index, t);
				}
			}
		}
		
		return mv;
	}

	@GetMapping("/mostrar-tarefas")
	public ModelAndView mostrarTarefas() {
		ModelAndView mv = new ModelAndView("mostrar-tarefas");
		mv.addObject("tarefas", tarefas);

		return mv;
	}

	@GetMapping("edit/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("create");

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getId() == id) {
				mv.addObject("tarefa", tarefa);
				break;
			}
		}

		return mv;
	}

	@GetMapping("/delete/{id}")
	public String deletar(@PathVariable Long id) {
		int index = 0;

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getId() == id) {
				index = tarefas.indexOf(tarefa);
				break;
			}
		}
		tarefas.remove(index);

		return "redirect:/mostrar-tarefas";
	}
}
