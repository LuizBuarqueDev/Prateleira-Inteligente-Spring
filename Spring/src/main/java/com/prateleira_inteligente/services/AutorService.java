package com.prateleira_inteligente.services;

import com.prateleira_inteligente.entities.Autor;
import com.prateleira_inteligente.entities.Livro;
import com.prateleira_inteligente.repositories.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    @Transactional
    public void deleteAutor(Autor autor) {
        // Desassociar o autor dos livros
        for (Livro livro : autor.getLivros()) {
            livro.setAutor(null);
        }
        autor.getLivros().clear();

        autorRepository.delete(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }
}