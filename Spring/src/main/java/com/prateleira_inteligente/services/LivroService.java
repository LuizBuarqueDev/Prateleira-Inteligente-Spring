package com.prateleira_inteligente.services;

import com.prateleira_inteligente.entities.Categoria;
import com.prateleira_inteligente.entities.Comentario;
import com.prateleira_inteligente.entities.Livro;
import com.prateleira_inteligente.entities.Usuario;
import com.prateleira_inteligente.persistence.ComentarioRepository;
import com.prateleira_inteligente.persistence.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final ComentarioRepository comentarioRepository;

    @Transactional
    public void deleteLivro(Livro livro) {
        // Remover associação com categorias
        for (Categoria categoria : livro.getCategorias()) {
            categoria.getLivros().remove(livro);
        }
        livro.getCategorias().clear();

        // Remover associação com usuários
        for (Usuario usuario : livro.getUsuarios()) {
            usuario.getLivros().remove(livro);
        }
        livro.getUsuarios().clear();

        // Remover comentários relacionados
        for (Comentario comentario : livro.getComentarios()) {
            if (comentario.getUsuario() != null) {
                comentario.getUsuario().getComentarios().remove(comentario);
            }
            comentarioRepository.delete(comentario);
        }
        livro.getComentarios().clear();

        // Por fim, remover o livro
        livroRepository.delete(livro);
    }
}
