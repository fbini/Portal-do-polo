package br.com.polo.cesmar.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.Map;

@Entity
@Table(name = "vagas")
@Data // Gera Getters, Setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_vaga")
    private String nomeVaga;

    @Column(name = "empresa_contratante")
    private String empresaContratante;

    @Column(name = "horario_funcionamento")
    private String horarioFuncionamento;

    private String descricao;

    private String requisitos;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    private String status;

    public void setDataPublicacao(LocalDate now) {
    }

    public Map<Object, Object> getNomeVaga() {
        return Map.of();
    }

    public void setStatus(String statusAtiva) {
    }

    public void setNomeVaga(Map<Object, Object> nomeVaga) {
    }

    public Object getDescricao() {
        return null;
    }

    public String getStatus() {
        return "";
    }

    public void toString(Object descricao) {

    }
}