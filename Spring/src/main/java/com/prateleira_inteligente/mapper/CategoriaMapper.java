package com.prateleira_inteligente.mapper;

import com.prateleira_inteligente.dto.CategoriaDTO;
import com.prateleira_inteligente.entities.Categoria;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoriaMapper {

    public CategoriaDTO toDTO(Categoria categoria) {
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .idLivros(categoria.getLivros().stream().map(id -> id.getId()).collect(Collectors.toList()))
                .build();
    }
}
