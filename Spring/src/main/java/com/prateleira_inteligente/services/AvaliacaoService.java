package com.prateleira_inteligente.services;

import com.prateleira_inteligente.entities.Avaliacao;
import com.prateleira_inteligente.repositories.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    @Transactional(readOnly = true)
    public List<Avaliacao> findAll() {
        return avaliacaoRepository.findAll();
    }
}
