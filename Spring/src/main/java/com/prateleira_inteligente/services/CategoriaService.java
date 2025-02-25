package com.prateleira_inteligente.services;

import com.prateleira_inteligente.entities.Categoria;
import com.prateleira_inteligente.entities.Livro;
import com.prateleira_inteligente.persistence.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    
    private final CategoriaRepository categoriaRepository;

    @Transactional
    public void deleteCategoria(Categoria categoria) {
        // Remover a categoria dos livros associados
        for (Livro livro : categoria.getLivros()) {
            livro.getCategorias().remove(categoria);
        }
        categoria.getLivros().clear();

        categoriaRepository.delete(categoria);
    }

}
