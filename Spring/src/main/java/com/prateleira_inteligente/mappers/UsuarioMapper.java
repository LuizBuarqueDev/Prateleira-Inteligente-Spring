package com.prateleira_inteligente.mappers;

import com.prateleira_inteligente.dto.UsuarioDTO;
import com.prateleira_inteligente.entities.Usuario;
import com.prateleira_inteligente.services.AvaliacaoService;
import com.prateleira_inteligente.services.ComentarioService;
import com.prateleira_inteligente.services.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final ComentarioService comentarioService;
    private final LivroService livroService;
    private final AvaliacaoService avaliacaoService;

    public UsuarioDTO toDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .idComentarios(usuario.getComentarios().stream().map(comentario ->  comentario.getId()).collect(Collectors.toList()))
                .idLivros(usuario.getLivros().stream().map(livro ->   livro.getId()).collect(Collectors.toList()))
                .idAvaliacoes(usuario.getAvaliacoes().stream().map(avaliacao ->  avaliacao.getId()).collect(Collectors.toList()))
                .build();
    }

    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setComentarios(comentarioService.findAllById(usuarioDTO.getIdComentarios()));
        usuario.setLivros(livroService.findAllById(usuarioDTO.getIdLivros()));
        usuario.setAvaliacoes(avaliacaoService.findAllById(usuarioDTO.getIdAvaliacoes()));
        return usuario;
    }
}
