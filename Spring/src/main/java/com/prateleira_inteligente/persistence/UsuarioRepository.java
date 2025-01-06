package com.prateleira_inteligente.persistence;

import com.prateleira_inteligente.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
