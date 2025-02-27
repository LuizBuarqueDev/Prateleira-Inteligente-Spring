package com.prateleira_inteligente.services;

import com.prateleira_inteligente.dto.ComentarioDTO;
import com.prateleira_inteligente.entities.Comentario;
import com.prateleira_inteligente.repositories.ComentarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    @Transactional
    public void deleteComentario(Comentario comentario) {
        // Remover o comentário da associação com o usuário e o livro
        if (comentario.getUsuario() != null) {
            comentario.getUsuario().getComentarios().remove(comentario);
        }
        if (comentario.getLivro() != null) {
            comentario.getLivro().getComentarios().remove(comentario);
        }
        comentarioRepository.delete(comentario);
    }

    @Transactional(readOnly = true)
    public List<Comentario> findAll(){
        return  comentarioRepository.findAll();
    }
}
