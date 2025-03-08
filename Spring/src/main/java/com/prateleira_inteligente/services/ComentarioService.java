package com.prateleira_inteligente.services;

import com.prateleira_inteligente.entities.Comentario;
import com.prateleira_inteligente.repositories.ComentarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioService implements IService<Comentario> {

    private final ComentarioRepository comentarioRepository;

    @Override
    @Transactional
    public Comentario save(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    @Transactional
    public Comentario update(Long id, Comentario comentario) {
        //TODO: Consertar, quando um livro e atualizado os autores que não foram atualizados perdem seus livros.
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Comentario getById(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario não encontrado com ID:" + id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Remover o comentário da associação com o usuário e o livro
        Comentario comentario = getById(id);
        if (comentario.getUsuario() != null) {
            comentario.getUsuario().getComentarios().remove(comentario);
        }
        if (comentario.getLivro() != null) {
            comentario.getLivro().getComentarios().remove(comentario);
        }
        comentarioRepository.delete(comentario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comentario> findAll(){
        return  comentarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comentario> findAllById(List<Long> ids) {
        return comentarioRepository.findAllById(ids);
    }
}
