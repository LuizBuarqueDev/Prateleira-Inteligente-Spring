package com.prateleira_inteligente.mapper;

import com.prateleira_inteligente.dto.AvalicaoDTO;
import com.prateleira_inteligente.entities.Avaliacao;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoMapper {

    public AvalicaoDTO toDTO(Avaliacao avaliacao) {
        return AvalicaoDTO.builder()
                .id(avaliacao.getId())
                .idLivro(avaliacao.getLivro().getId())
                .idUsuario(avaliacao.getUsuario().getId())
                .build();
    }
}
