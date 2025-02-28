package com.prateleira_inteligente.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UsuarioDTO {
    private Long id;
    private String nome;
    private List<Long> idComentarios;
    private List<Long> idLivros;
    private List<Long> idAvaliacoes;
}
