package br.com.springmvc.controllers;

import java.util.HashMap;
import java.util.Map;

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

	Map<Long, Tarefa> tarefas = new HashMap<>();

	@GetMapping("/create")
	public ModelAndView home(Tarefa tarefa) {
		ModelAndView mv = new ModelAndView("create");
		return mv;
	}

	@PostMapping("/create")
	public ModelAndView create(@Valid Tarefa t, BindingResult result) {

		if (result.hasErrors()) {
			return home(t);
		}

		ModelAndView mv = new ModelAndView("redirect:/mostrar-tarefas");

		if (t.getId() == null) {
			Long id = null;
			Long cont = 1L;

			while (true) {
				id = tarefas.size() + cont;

				if (tarefas.get(id) == null) {
					break;
				}
				cont++;
			}

			Tarefa tarefa = new Tarefa(id, t.getTarefa(), t.getData(), t.getHora());
			tarefas.put(id, tarefa);
		} else {
			tarefas.remove(t.getId());
			tarefas.put(t.getId(), t);
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
		mv.addObject("tarefa", tarefas.get(id));

		return mv;
	}

	@GetMapping("/delete/{id}")
	public String deletar(@PathVariable Long id) {

		tarefas.remove(id);
		return "redirect:/mostrar-tarefas";
	}
}
