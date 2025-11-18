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

    // [ENDPOINT 1] GET /vagas: Exibe a lista de vagas ativas
    @GetMapping
    public String listarVagas(Model model) {
        model.addAttribute("listaVagas", vagaService.listarVagasAtivas());
        return "vagas/lista";
    }

    // [ENDPOINT 2] GET /vagas/nova: Exibe o formulário de cadastro
    @GetMapping("/nova")
    public ModelAndView exibirFormularioNovaVaga() {
        ModelAndView mv = new ModelAndView("vagas/formulario");
        mv.addObject("vaga", new Vaga());
        return mv;
    }

    // [ENDPOINT 3] POST /vagas/salvar: Processa o cadastro (COM TRATAMENTO DE ERRO)
    @PostMapping("/salvar")
    public String salvarVaga(@ModelAttribute Vaga vaga, Model model) {
        try {
            // Tenta salvar a vaga. O Service lançará IllegalArgumentException se o nome estiver vazio.
            vagaService.criarVaga(vaga);
            return "redirect:/vagas?sucesso=true"; // Redireciona com sucesso
        } catch (IllegalArgumentException e) {
            // Captura a exceção de validação

            // 1. Adiciona a mensagem de erro ao modelo (para o bloco th:if="${erro}" no HTML)
            model.addAttribute("erro", e.getMessage());

            // 2. Adiciona o objeto 'vaga' de volta para manter os campos preenchidos
            model.addAttribute("vaga", vaga);

            // 3. Retorna o próprio template do formulário
            return "vagas/formulario";
        }
    }

    //EXCLUIR VAGA
    @GetMapping("/vagas/excluir/{id}")
    public String excluirVaga(@PathVariable Long id){
        vagaService.deletarVaga(id);
        return "redirect:/vagas"; // volta para listagem
    }
}

