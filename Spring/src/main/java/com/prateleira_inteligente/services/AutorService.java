package com.prateleira_inteligente.services;

import com.prateleira_inteligente.entities.Autor;
import com.prateleira_inteligente.repositories.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService implements IService<Autor> {

    private final AutorRepository autorRepository;

    @Override
    @Transactional
    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    @Transactional
    public Autor update(Long id, Autor autor) {
        return autorRepository.findById(id)
                .map(autorExistente -> {
                    autorExistente.setNome(autor.getNome());
                    return autorRepository.save(autorExistente);
                })
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Autor getById(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID:" + id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        autorRepository.delete(getById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }
}