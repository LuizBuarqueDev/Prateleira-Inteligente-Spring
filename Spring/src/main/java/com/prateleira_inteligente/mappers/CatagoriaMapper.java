package com.prateleira_inteligente.mappers;

import com.prateleira_inteligente.dto.CategoriaDTO;
import com.prateleira_inteligente.entities.Categoria;
import com.prateleira_inteligente.services.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CatagoriaMapper implements IMapper<Categoria, CategoriaDTO> {

    private final LivroService livroService;

    @Override
    public CategoriaDTO toDTO(Categoria categoria) {
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .idLivros(categoria.getLivros().stream().map(id -> id.getId()).collect(Collectors.toList()))
                .build();
    }

    @Override
    public Categoria toEntity(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setId(categoriaDTO.getId());
        categoria.setNome(categoriaDTO.getNome());
        categoria.setLivros(livroService.findAllById(categoriaDTO.getIdLivros()));
        return categoria;
    }
}
