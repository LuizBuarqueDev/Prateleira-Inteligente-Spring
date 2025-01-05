package com.prateleira_inteligente.persistence;

import com.prateleira_inteligente.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro,Long> {
}
