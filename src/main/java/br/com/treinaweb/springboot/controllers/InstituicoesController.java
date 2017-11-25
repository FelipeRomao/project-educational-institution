package br.com.treinaweb.springboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.springboot.entidades.Instituicao;
import br.com.treinaweb.springboot.repositorios.RepositorioInstituicao;

@Controller
@RequestMapping("/instituicoes")
public class InstituicoesController {

	@Autowired
	private RepositorioInstituicao repositorio;

	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView resultado = new ModelAndView("instituicao/index");
		List<Instituicao> instituicoes = repositorio.findAll();
		resultado.addObject("instituicoes", instituicoes);
		return resultado;
	}

	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView resultado = new ModelAndView("instituicao/inserir");
		resultado.addObject("instituicao", new Instituicao());
		return resultado;
	}

	@PostMapping("/inserir")
	public String inserir(Instituicao instituicao) {
		repositorio.save(instituicao);
		return "redirect:/instituicoes/index";
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Instituicao instituicao = repositorio.findOne(id);
		ModelAndView resultado = new ModelAndView("instituicao/editar");
		resultado.addObject("instituicao", instituicao);
		return resultado;
	}
	
	@PostMapping("/editar")
	public String editar(Instituicao instituicao){
		repositorio.save(instituicao);
		return "redirect:/instituicoes/index";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id){
		repositorio.delete(id);
		return "redirect:/instituicoes/index";
	}

	@GetMapping({"/pesquisarPorNome/{nome}", "/pesquisarPorNome"})
	public @ResponseBody List<Instituicao> pesquisarPorNome(@PathVariable Optional<String> nome){
		if(nome.isPresent()){
			return repositorio.findByNomeContaining(nome.get());
		} else {
			return repositorio.findAll();
		}
	}
}
