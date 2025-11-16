package br.com.polo.cesmar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
// ... outras anotações

@Entity
public class Candidatura {

    @Id
    private Long id; // ID da Candidatura

    // Campo para ligar a candidatura à Vaga
    // private Vaga vaga; // Você pode usar @ManyToOne aqui

    private String nomeCandidato;
    private String email;
    private String telefone;
    private String linkCurriculo; // URL para um currículo

    // Getters, Setters e Construtores...
}