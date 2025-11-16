package br.com.polo.cesmar.service;

import br.com.polo.cesmar.model.Candidatura;
import br.com.polo.cesmar.repository.CandidaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidaturaService {

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    public Candidatura salvarCandidatura(Candidatura candidatura) {
        return candidaturaRepository.save(candidatura);
    }
}