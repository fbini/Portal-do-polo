package br.com.polo.cesmar.service;

import br.com.polo.cesmar.model.Vaga;
import br.com.polo.cesmar.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException; // 👈 Importante para o tratamento de erro

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    // Cria/Salva uma nova vaga
    public Vaga criarVaga(Vaga vaga) {
        // Assume que 'nomeVaga' é usado em vez do antigo 'titulo'
        if (vaga.getNomeVaga() == null || vaga.getNomeVaga().isEmpty()) {
            throw new IllegalArgumentException("O nome da vaga é obrigatório.");
        }
        vaga.setDataPublicacao(LocalDate.now());
        vaga.setStatus("ATIVA");
        return vagaRepository.save(vaga);
    }

    // Lista todas as vagas ativas
    public List<Vaga> listarVagasAtivas() {
        return vagaRepository.findByStatus("ATIVA");
    }

    // Método CORRIGIDO: Busca uma vaga por ID.
    // Lança NoSuchElementException se a vaga não for encontrada.
    public Vaga buscarVagaPorId(Long id) {
        return vagaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Vaga não encontrada com ID: " + id)
        );
    }
}