package com.prateleira_inteligente.services;

import com.prateleira_inteligente.entities.Avaliacao;
import com.prateleira_inteligente.repositories.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoService implements IService<Avaliacao> {

    private final AvaliacaoRepository avaliacaoRepository;

    @Override
    @Transactional
    public Avaliacao save(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    @Override
    @Transactional
    public Avaliacao update(Long id, Avaliacao avaliacao) {
        return null;
    }

    @Override
    @Transactional
    public Avaliacao getById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {

    }

    @Override
    @Transactional
    public List<Avaliacao> findAll() {
        return List.of();
    }
}
