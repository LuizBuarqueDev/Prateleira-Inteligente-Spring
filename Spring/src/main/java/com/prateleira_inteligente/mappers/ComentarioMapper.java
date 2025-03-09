package com.prateleira_inteligente.mappers;

import com.prateleira_inteligente.dto.ComentarioDTO;
import com.prateleira_inteligente.entities.Comentario;
import com.prateleira_inteligente.services.LivroService;
import com.prateleira_inteligente.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComentarioMapper {

    private final UsuarioService usuarioService;
    private final LivroService livroService;

    public ComentarioDTO toDTO(Comentario comentario) {
        return ComentarioDTO.builder()
                .id(comentario.getId())
                .usuarioId(comentario.getUsuario().getId())
                .livroId(comentario.getLivro().getId())
                .texto(comentario.getTexto())
                .dataCriacao(comentario.getDataCriacao())
                .build();
    }

    public Comentario toEntity(ComentarioDTO comentarioDTO) {
        Comentario comentario = new Comentario();
        comentario.setId(comentarioDTO.getId());
        comentario.setUsuario(usuarioService.getById(comentarioDTO.getUsuarioId()));
        comentario.setLivro(livroService.getById(comentarioDTO.getLivroId()));
        comentario.setTexto(comentarioDTO.getTexto());
        comentario.setDataCriacao(comentarioDTO.getDataCriacao());
        return comentario;
    }
}
