package com.prateleira_inteligente.mapper;

import com.prateleira_inteligente.dto.UsuarioDTO;
import com.prateleira_inteligente.entities.Usuario;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .idComentarios(usuario.getComentarios().stream().map(comentario ->  comentario.getId()).collect(Collectors.toList()))
                .idLivros(usuario.getLivros().stream().map(livro ->   livro.getId()).collect(Collectors.toList()))
                .idAvaliacoes(usuario.getAvaliacoes().stream().map(avaliacao ->  avaliacao.getId()).collect(Collectors.toList()))
                .build();
    }
}
