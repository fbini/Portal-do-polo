package br.com.polo.cesmar.repository;

import br.com.polo.cesmar.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

    // Busca todas as vagas por status (usado para listar apenas ATIVAS)
    List<Vaga> findByStatus(String status);
}