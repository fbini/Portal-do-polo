package br.com.polo.cesmar.controller;

import br.com.polo.cesmar.model.Vaga;
import br.com.polo.cesmar.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/vagas")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    // [ENDPOINT 1] GET /vagas: Exibe a lista de vagas ativas (Visão do Usuário)
    @GetMapping
    public String listarVagas(Model model) {
        model.addAttribute("listaVagas", vagaService.listarVagasAtivas());
        // Retorna o template "vagas/lista" (que será src/main/resources/templates/vagas/lista.html)
        return "vagas/lista";
    }

    // [ENDPOINT 2] GET /vagas/nova: Exibe o formulário de cadastro (Visão do Admin)
    @GetMapping("/nova")
    public ModelAndView exibirFormularioNovaVaga() {
        ModelAndView mv = new ModelAndView("vagas/formulario");
        // Passa um objeto Vaga vazio para o Thymeleaf preencher
        mv.addObject("vaga", new Vaga());
        return mv;
    }

    // [ENDPOINT 3] POST /vagas/salvar: Processa o cadastro (Ação do Admin)
    @PostMapping("/salvar")
    public String salvarVaga(@ModelAttribute Vaga vaga) {
        vagaService.criarVaga(vaga);
        // Redireciona para a listagem após salvar com sucesso
        return "redirect:/vagas";
    }
}