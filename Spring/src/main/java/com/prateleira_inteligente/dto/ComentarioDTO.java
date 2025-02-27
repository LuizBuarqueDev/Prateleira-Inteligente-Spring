package com.prateleira_inteligente.dto;

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
}
