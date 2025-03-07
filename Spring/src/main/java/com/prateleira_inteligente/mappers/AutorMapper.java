package com.prateleira_inteligente.mappers;

import com.prateleira_inteligente.dto.AutorDTO;
import com.prateleira_inteligente.entities.Autor;
import com.prateleira_inteligente.services.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AutorMapper {

    private final LivroService livroService;

    public AutorDTO toDTO(Autor autor) {
        return AutorDTO.builder()
                .id(autor.getId())
                .nome(autor.getNome())
                .idLivros(autor.getLivros().stream().map(id -> id.getId()).collect(Collectors.toList()))
                .build();
    }

    public Autor toEntity(AutorDTO autorDTO) {
        Autor autor = new Autor();
        autor.setId(autorDTO.getId());
        autor.setNome(autorDTO.getNome());
        autor.setLivros(livroService.findAllById(autorDTO.getIdLivros()));
        return autor;
    }
}
