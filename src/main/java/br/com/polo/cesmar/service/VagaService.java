package br.com.polo.cesmar.service;

import br.com.polo.cesmar.model.Vaga;
import br.com.polo.cesmar.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException; // Importe esta classe

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    // Cria/Salva uma nova vaga
    public Vaga criarVaga(Vaga vaga) {
        vaga.setDataPublicacao(LocalDate.now());
        vaga.setStatus("ATIVA");
        return vagaRepository.save(vaga);
    }

    // Lista todas as vagas ativas
    public List<Vaga> listarVagasAtivas() {
        return vagaRepository.findByStatus("ATIVA");
    }

    // Busca uma vaga por ID (corrigido para retornar Vaga e lançar exceção se não encontrar)
    public Vaga buscarVagaPorId(Long id) { // Retorna Vaga diretamente
        // orElseThrow() desempacota o Optional. Se o ID não for encontrado,
        // ele lança NoSuchElementException (que é convertida em erro 500/404 pelo Spring).
        return vagaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Vaga não encontrada com ID: " + id)
        );
    }
}