package com.prateleira_inteligente.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AvalicaoDTO {
    private Long id;
    private Long idUsuario;
    private Long idLivro;
}
