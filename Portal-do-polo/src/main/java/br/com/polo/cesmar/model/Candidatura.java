package br.com.polo.cesmar.model;

import jakarta.persistence.*;
// Se você está usando Lombok, inclua @Data, @NoArgsConstructor, etc.
// Se não, você deve adicionar todos os Getters/Setters e o construtor padrão (vazio) manualmente.

@Entity
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento Many-to-One: Muitas Candidaturas para Uma Vaga
    @ManyToOne
    @JoinColumn(name = "vaga_id", nullable = false)
    private Vaga vaga;

    private String nomeCandidato;
    private String email;
    private String telefone;
    private String linkCurriculo;

    // Construtor padrão obrigatório para JPA
    public Candidatura() {
    }

    public Vaga getVaga() { return vaga; }
    public void setVaga(Vaga vaga) { this.vaga = vaga; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeCandidato() { return nomeCandidato; }
    public void setNomeCandidato(String nomeCandidato) { this.nomeCandidato = nomeCandidato; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getLinkCurriculo() { return linkCurriculo; }
    public void setLinkCurriculo(String linkCurriculo) { this.linkCurriculo = linkCurriculo; }
}