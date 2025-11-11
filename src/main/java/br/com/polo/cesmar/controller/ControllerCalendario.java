package br.com.polo.cesmar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ControllerCalendario {

    private final Map<LocalDate, List<String>> anotacoes = new HashMap<>();
    private static final int MAX_ANOTACOES = 5;

    @GetMapping("/calendario")
    public String calendario(
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) Integer mes,
            Model model) {

        LocalDate hoje = LocalDate.now();
        int anoAtual = (ano != null) ? ano : hoje.getYear();
        int mesAtual = (mes != null) ? mes : hoje.getMonthValue();

        LocalDate primeiroDia = LocalDate.of(anoAtual, mesAtual, 1);
        int diasNoMes = primeiroDia.lengthOfMonth();

        // Lista de todos os dias do mês
        List<LocalDate> diasDoMes = new ArrayList<>();
        for (int i = 1; i <= diasNoMes; i++) {
            LocalDate data = LocalDate.of(anoAtual, mesAtual, i);
            diasDoMes.add(data);
            anotacoes.putIfAbsent(data, new ArrayList<>());
        }

        // Cálculo do primeiro dia da semana (domingo = 0, sábado = 6)
        int primeiroDiaSemana = primeiroDia.getDayOfWeek().getValue();
        // Ajustar para domingo = 0
//        primeiroDiaSemana = (primeiroDiaSemana == 7) ? 0 : primeiroDiaSemana;

        model.addAttribute("ano", anoAtual);
        model.addAttribute("mes", mesAtual);
        model.addAttribute("nomeMes", primeiroDia.getMonth().name());
        model.addAttribute("diasDoMes", diasDoMes);
        model.addAttribute("anotacoes", anotacoes);
        model.addAttribute("primeiroDiaSemana", primeiroDiaSemana);

        return "calendario";
    }

    @PostMapping("/anotar")
    public String adicionarAnotacao(
            @RequestParam String data,
            @RequestParam String texto,
            Model model) {

        if (texto == null || texto.isBlank()) {
            // Mensagem de erro: anotação vazia
            model.addAttribute("erro", "A anotação não pode estar vazia.");
            return "redirect:/calendario";
        }

        LocalDate dia;
        try {
            dia = LocalDate.parse(data);
        } catch (DateTimeParseException e) {
            model.addAttribute("erro", "Data inválida.");
            return "redirect:/calendario";
        }

        List<String> listaAnotacoes = anotacoes.computeIfAbsent(dia, d -> new ArrayList<>());

        if (listaAnotacoes.size() >= MAX_ANOTACOES) {
            model.addAttribute("erro", "Máximo de 5 anotações por dia atingido.");
            return "redirect:/calendario?ano=" + dia.getYear() + "&mes=" + dia.getMonthValue();
        }

        listaAnotacoes.add(texto.trim());

        // Redirecionar para o mês e ano da anotação adicionada
        return "redirect:/calendario?ano=" + dia.getYear() + "&mes=" + dia.getMonthValue();
    }
}