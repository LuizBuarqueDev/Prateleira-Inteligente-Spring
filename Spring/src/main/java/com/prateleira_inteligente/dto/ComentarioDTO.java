package com.prateleira_inteligente.dto;

import com.prateleira_inteligente.entities.Comentario;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ComentarioDTO {
    private Long id;
    private Long usuarioId;
    private Long livroId;
    private String texto;
    private LocalDateTime dataCriacao;

    public static ComentarioDTO toDTO(Comentario comentario) {
        return ComentarioDTO.builder()
                .id(comentario.getId())
                .usuarioId(comentario.getUsuario().getId())
                .livroId(comentario.getLivro().getId())
                .texto(comentario.getTexto())
                .dataCriacao(comentario.getDataCriacao())
                .build();
    }
}
