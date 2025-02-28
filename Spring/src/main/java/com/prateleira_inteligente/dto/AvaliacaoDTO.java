package com.prateleira_inteligente.dto;

import com.prateleira_inteligente.entities.Avaliacao;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AvaliacaoDTO {
    private Long id;
    private Long idUsuario;
    private Long idLivro;

    public static AvaliacaoDTO toDTO(Avaliacao avaliacao) {
        return AvaliacaoDTO.builder()
                .id(avaliacao.getId())
                .idLivro(avaliacao.getLivro().getId())
                .idUsuario(avaliacao.getUsuario().getId())
                .build();
    }
}
