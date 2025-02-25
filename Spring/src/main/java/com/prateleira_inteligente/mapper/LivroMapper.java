package com.prateleira_inteligente.mapper;

import com.prateleira_inteligente.dto.LivroDTO;
import com.prateleira_inteligente.entities.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public LivroDTO toDTO(Livro livro) {
        return LivroDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .anoPublicacao(livro.getAnoPublicacao())
                .descricao(livro.getDescricao())
                .editora(livro.getEditora())
                .build();
    }
}
