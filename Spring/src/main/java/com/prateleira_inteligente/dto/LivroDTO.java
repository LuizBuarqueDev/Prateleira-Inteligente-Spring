package com.prateleira_inteligente.dto;

import lombok.Builder;
import lombok.Data;
import java.sql.Date;
import java.util.List;

@Data
@Builder
public class LivroDTO {
    private Long id;
    private String titulo;
    private Date anoPublicacao;
    private String descricao;
    private String editora;
    private List<Long> idCategoria ;
}