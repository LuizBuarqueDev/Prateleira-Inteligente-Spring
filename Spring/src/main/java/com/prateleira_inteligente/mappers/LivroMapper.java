package com.prateleira_inteligente.mappers;

import com.prateleira_inteligente.dto.LivroDTO;
import com.prateleira_inteligente.entities.Livro;
import com.prateleira_inteligente.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LivroMapper implements IMapper<Livro, LivroDTO> {

    private final AutorService autorService;
    private final CategoriaService categoriaService;
    private final ComentarioService comentarioService;
    private final UsuarioService usuarioService;

    public LivroDTO toDTO(Livro livro) {
        return LivroDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .anoPublicacao(livro.getAnoPublicacao())
                .descricao(livro.getDescricao())
                .editora(livro.getEditora())
                .idAutor(livro.getId())
                .idCategorias(livro.getCategorias().stream().map(categoria -> categoria.getId()).collect(Collectors.toList()))
                .idComentarios(livro.getComentarios().stream().map(comentario -> comentario.getId()).collect(Collectors.toList()))
                .idUsuarios(livro.getUsuarios().stream().map(usuario -> usuario.getId()).collect(Collectors.toList()))
                .build();
    }

    public Livro toEntity(LivroDTO livroDTO) {
        Livro livro = new Livro();
        livro.setId(livroDTO.getId());
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAnoPublicacao(livroDTO.getAnoPublicacao());
        livro.setDescricao(livroDTO.getDescricao());
        livro.setEditora(livroDTO.getEditora());
        livro.setAutor(autorService.getById(livroDTO.getIdAutor()));
        livro.setCategorias(categoriaService.findAllById(livroDTO.getIdCategorias()));
        livro.setComentarios(comentarioService.findAllById(livroDTO.getIdComentarios()));
        livro.setUsuarios(usuarioService.findAllById(livroDTO.getIdUsuarios()));
        return livro;
    }
}
