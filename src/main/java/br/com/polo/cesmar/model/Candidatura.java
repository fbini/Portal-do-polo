package br.com.polo.cesmar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

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

    public Candidatura(Long id, String nomeCandidato, String email, String telefone, String linkCurriculo) {
        this.id = id;
        this.nomeCandidato = nomeCandidato;
        this.email = email;
        this.telefone = telefone;
        this.linkCurriculo = linkCurriculo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCandidato() {
        return nomeCandidato;
    }

    public void setNomeCandidato(String nomeCandidato) {
        this.nomeCandidato = nomeCandidato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLinkCurriculo() {
        return linkCurriculo;
    }

    public void setLinkCurriculo(String linkCurriculo) {
        this.linkCurriculo = linkCurriculo;
    }
}