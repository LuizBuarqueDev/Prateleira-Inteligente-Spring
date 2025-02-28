package com.prateleira_inteligente.dto;

import com.prateleira_inteligente.entities.Usuario;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UsuarioDTO {
    private Long id;
    private String nome;
    private List<Long> idComentarios;
    private List<Long> idLivros;
    private List<Long> idAvaliacoes;

    public static UsuarioDTO toDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .idComentarios(usuario.getComentarios().stream().map(comentario ->  comentario.getId()).collect(Collectors.toList()))
                .idLivros(usuario.getLivros().stream().map(livro ->   livro.getId()).collect(Collectors.toList()))
                .idAvaliacoes(usuario.getAvaliacoes().stream().map(avaliacao ->  avaliacao.getId()).collect(Collectors.toList()))
                .build();
    }
}
