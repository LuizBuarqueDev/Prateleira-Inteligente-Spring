package com.prateleira_inteligente.dto;

import com.prateleira_inteligente.entities.Autor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class AutorDTO {
    private Long id;
    private String nome;
    private List<Long> idLivros;

    public static AutorDTO toDTO(Autor autor) {
        return AutorDTO.builder()
                .id(autor.getId())
                .nome(autor.getNome())
                .idLivros(autor.getLivros().stream().map(id -> id.getId()).collect(Collectors.toList()))
                .build();
    }

    public Autor toEntity() {
        Autor autor = new Autor();
        autor.setNome(nome);
        return autor;
    }
}
