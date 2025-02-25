package com.prateleira_inteligente.dto;

import com.prateleira_inteligente.entities.Livro;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoriaDTO {
    private Long id;
    private String nome;
    private List<Long> idLivros;
}
