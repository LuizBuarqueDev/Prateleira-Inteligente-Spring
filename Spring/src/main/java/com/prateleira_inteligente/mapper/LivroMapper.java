package com.prateleira_inteligente.mapper;

import com.prateleira_inteligente.dto.LivroDTO;
import com.prateleira_inteligente.entities.Livro;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LivroMapper {

    public LivroDTO toDTO(Livro livro) {
        return LivroDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .anoPublicacao(livro.getAnoPublicacao())
                .descricao(livro.getDescricao())
                .editora(livro.getEditora())
                .idAutor(livro.getId())
                .idCategoria(livro.getCategorias().stream().map(categoria -> categoria.getId()).collect(Collectors.toList()))
                .idComentario(livro.getComentarios().stream().map(comentario -> comentario.getId()).collect(Collectors.toList()))
                .build();
    }
}