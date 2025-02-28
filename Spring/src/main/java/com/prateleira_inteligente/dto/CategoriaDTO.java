package com.prateleira_inteligente.dto;

import com.prateleira_inteligente.entities.Categoria;
import com.prateleira_inteligente.entities.Livro;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CategoriaDTO {
    private Long id;
    private String nome;
    private List<Long> idLivros;

    public static CategoriaDTO toDTO(Categoria categoria) {
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .idLivros(categoria.getLivros().stream().map(id -> id.getId()).collect(Collectors.toList()))
                .build();
    }
}
