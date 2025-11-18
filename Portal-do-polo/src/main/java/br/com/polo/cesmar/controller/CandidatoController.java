package br.com.polo.cesmar.controller;

import br.com.polo.cesmar.model.Vaga;
import br.com.polo.cesmar.model.Candidatura;
import br.com.polo.cesmar.service.VagaService;
import br.com.polo.cesmar.service.CandidaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/candidatar")
public class CandidatoController {

    @Autowired
    private VagaService vagaService;

    @Autowired
    private CandidaturaService candidaturaService;

    // NOVO: Redireciona a rota base /candidatar para /vagas
    @GetMapping
    public String redirecionarParaVagas() {
        return "redirect:/vagas";
    }

    // GET /candidatar/{id}: Exibe o formulário de candidatura
    @GetMapping("/{id}")
    public String exibirFormularioCandidatura(@PathVariable("id") Long vagaId, Model model) {

        // 1. Busca a vaga. Se não encontrar, lança exceção (tratada).
        Vaga vaga = vagaService.buscarVagaPorId(vagaId);

        // 2. Adiciona objetos ao modelo
        model.addAttribute("vaga", vaga);
        model.addAttribute("candidatura", new Candidatura());

        // 3. Retorna o template
        return "candidatar/formulario";
    }

    // POST /candidatar/salvar: Salva a candidatura no banco de dados
    @PostMapping("/salvar")
    public String salvarCandidatura(@ModelAttribute Candidatura candidatura, @RequestParam("vagaId") Long vagaId) {

        // 1. Busca a vaga para ligar a candidatura a ela
        Vaga vaga = vagaService.buscarVagaPorId(vagaId);

        // 2. Associa a vaga ao objeto de candidatura
        candidatura.setVaga(vaga);

        // 3. Salva a candidatura
        candidaturaService.salvarCandidatura(candidatura);

        // 4. Redireciona para uma página de sucesso
        return "redirect:/vagas?sucesso=candidatura";
    }

    // Tratamento de exceção para vagas não encontradas (retorna erro/404)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String handleVagaNotFound(Model model) {
        model.addAttribute("mensagem", "A vaga solicitada não foi encontrada.");
        return "erro/404";
    }
}