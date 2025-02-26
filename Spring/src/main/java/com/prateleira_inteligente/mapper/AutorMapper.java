package com.prateleira_inteligente.mapper;

import com.prateleira_inteligente.dto.AutorDTO;
import com.prateleira_inteligente.entities.Autor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AutorMapper {

    public AutorDTO toDTO(Autor autor) {
        return AutorDTO.builder()
                .id(autor.getId())
                .nome(autor.getNome())
                .idLivros(autor.getLivros().stream().map(id -> id.getId()).collect(Collectors.toList()))
                .build();
    }
}
