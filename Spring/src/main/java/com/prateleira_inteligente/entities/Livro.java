package com.prateleira_inteligente.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

@Entity
@Table(name = "Livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private java.sql.Date anoPublicacao;
    private String descricao;
    private String editora;

    @ManyToMany(mappedBy = "livros", cascade = CascadeType.ALL)
    private List<Categoria> categorias = new ArrayList<>();
}
