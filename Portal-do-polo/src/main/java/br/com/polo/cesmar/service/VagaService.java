package br.com.polo.cesmar.service;

import br.com.polo.cesmar.model.Vaga;
import br.com.polo.cesmar.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; //  Novo Import
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VagaService {

    private static final String STATUS_ATIVA = "ATIVA"; //  Constante para Status
    private static final String STATUS_INATIVA = "INATIVA";

    @Autowired
    private VagaRepository vagaRepository;

    // 1. CRIA/SALVA UMA NOVA VAGA
    @Transactional
    public Vaga criarVaga(Vaga vaga) {
        if (vaga.getNomeVaga() == null || vaga.getNomeVaga().isEmpty()) {
            throw new IllegalArgumentException("O nome da vaga é obrigatório.");
        }
        vaga.setDataPublicacao(LocalDate.now());
        vaga.setStatus(STATUS_ATIVA); // Uso da constante
        return vagaRepository.save(vaga);
    }

    // 2. BUSCA VAGA POR ID
    @Transactional(readOnly = true) // Apenas leitura
    public Vaga buscarVagaPorId(Long id) {
        return vagaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Vaga não encontrada com ID: " + id)
        );
    }

    // 3. LISTA TODAS AS VAGAS
    @Transactional(readOnly = true)
    public List<Vaga> listarTodasVagas() {
        return vagaRepository.findAll();
    }

    // 4. LISTA SOMENTE AS VAGAS ATIVAS
    @Transactional(readOnly = true)
    public List<Vaga> listarVagasAtivas() {
        return vagaRepository.findByStatus(STATUS_ATIVA);
    }

    // 5. ATUALIZA UMA VAGA EXISTENTE
    @Transactional
    public Vaga atualizarVaga(Long id, Vaga vagaAtualizada) {
        Vaga vagaExistente = buscarVagaPorId(id);

        if (vagaAtualizada.getNomeVaga() == null || vagaAtualizada.getNomeVaga().isEmpty()) {
            throw new IllegalArgumentException("O nome da vaga não pode ser vazio na atualização.");
        }

        // Atualiza campos relevantes (preserva DataPublicacao e ID)
        vagaExistente.setNomeVaga(vagaAtualizada.getNomeVaga());
        vagaExistente.toString(vagaAtualizada.getDescricao());
        vagaExistente.setStatus(vagaAtualizada.getStatus());

        // O save() é implícito no @Transactional, mas é bom chamá-lo para clareza
        return vagaRepository.save(vagaExistente);
    }

    // 6. INATIVA UMA VAGA (Melhor que deletar em muitos casos)
    @Transactional
    public Vaga inativarVaga(Long id) {
        Vaga vagaExistente = buscarVagaPorId(id);
        vagaExistente.setStatus(STATUS_INATIVA);
        return vagaRepository.save(vagaExistente);
    }

    // 7. DELETA VAGA (Se for realmente necessário a exclusão física)
    @Transactional
    public void deletarVaga(Long id){
        // Usamos buscarVagaPorId primeiro para garantir que a exceção NoSuchElementException
        // seja lançada se o ID for inválido, fornecendo um feedback melhor ao usuário.
        Vaga vaga = buscarVagaPorId(id);
        vagaRepository.delete(vaga);
    }
}