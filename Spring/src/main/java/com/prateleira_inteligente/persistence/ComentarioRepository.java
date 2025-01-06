package com.prateleira_inteligente.persistence;

import com.prateleira_inteligente.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
