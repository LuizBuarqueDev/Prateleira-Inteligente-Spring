package com.prateleira_inteligente.mapper;

import com.prateleira_inteligente.dto.ComentarioDTO;
import com.prateleira_inteligente.entities.Comentario;
import org.springframework.stereotype.Component;

@Component
public class ComentarioMapper {

    public ComentarioDTO toDTO(Comentario comentario) {
        return ComentarioDTO.builder()
                .id(comentario.getId())
                .usuarioId(comentario.getUsuario().getId())
                .livroId(comentario.getLivro().getId())
                .texto(comentario.getTexto())
                .dataCriacao(comentario.getDataCriacao())
                .build();
    }
}
