package br.com.polo.cesmar.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "vagas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // NOVO/ATUALIZADO: Nome da Vaga (título explícito)
    @Column(name = "nome_vaga")
    private String nomeVaga;

    // NOVO: Empresa Contratante
    @Column(name = "empresa_contratante")
    private String empresaContratante;

    // NOVO: Horário de Funcionamento
    @Column(name = "horario_funcionamento")
    private String horarioFuncionamento;

    // Descrição da Vaga (mantido)
    private String descricao;

    // Requisitos Mínimos (mantido)
    private String requisitos;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    private String status; // ATIVA, INATIVA, ENCERRADA

    public void setDataPublicacao(LocalDate now) {
    }

    public void setStatus(String ativa) {
    }

    public CharSequence getNomeVaga() {
        return null;
    }

    // Se você não está usando Lombok, precisará gerar os Getters/Setters manualmente
}