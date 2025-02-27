package com.prateleira_inteligente.mapper;

import com.prateleira_inteligente.dto.AvaliacaoDTO;
import com.prateleira_inteligente.entities.Avaliacao;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoMapper {

    public AvaliacaoDTO toDTO(Avaliacao avaliacao) {
        return AvaliacaoDTO.builder()
                .id(avaliacao.getId())
                .idLivro(avaliacao.getLivro().getId())
                .idUsuario(avaliacao.getUsuario().getId())
                .build();
    }
}
