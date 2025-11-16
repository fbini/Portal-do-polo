package br.com.polo.cesmar.controller;

import br.com.polo.cesmar.model.Vaga;
import br.com.polo.cesmar.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/candidatar")
public class CandidatoController {

    @Autowired
    private VagaService vagaService;

    // Mapeia para http://localhost:8080/candidatar/1
    @GetMapping("/{id}")
    public String exibirFormularioCandidatura(@PathVariable("id") Long vagaId, Model model) {

        // 1. Busca a vaga. Se não encontrar, o Service lança exceção.
        Vaga vaga = vagaService.buscarVagaPorId(vagaId);

        // 2. Adiciona objetos ao modelo
        model.addAttribute("vaga", vaga);
        // model.addAttribute("candidatura", new Candidatura()); // Adicione seu objeto de candidatura aqui

        // 3. Retorna o template do formulário
        return "candidatar/formulario";
    }

    // Método para lidar com a exceção se a vaga não for encontrada (ID inválido)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Retorna HTTP 404
    @ExceptionHandler(NoSuchElementException.class)
    public String handleVagaNotFound(Model model) {
        model.addAttribute("mensagem", "A vaga solicitada não foi encontrada.");
        return "erro/404"; // Retorna para um template de erro 404
    }

    // Adicione aqui um @PostMapping para salvar a candidatura
}