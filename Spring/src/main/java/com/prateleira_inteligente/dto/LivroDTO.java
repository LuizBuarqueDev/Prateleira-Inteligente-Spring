package com.prateleira_inteligente.dto;

import com.prateleira_inteligente.entities.Livro;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class LivroDTO {
    private Long id;
    private String titulo;
    private LocalDate anoPublicacao;
    private String descricao;
    private String editora;
    private Long idAutor;
    private List<Long> idCategoria ;
    private List<Long> idComentario;

    public static LivroDTO toDTO(Livro livro) {
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