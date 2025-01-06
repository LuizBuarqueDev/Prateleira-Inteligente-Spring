package com.prateleira_inteligente.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
