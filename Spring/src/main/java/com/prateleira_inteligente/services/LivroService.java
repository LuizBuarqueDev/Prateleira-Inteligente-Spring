package com.prateleira_inteligente.services;

import com.prateleira_inteligente.entities.Categoria;
import com.prateleira_inteligente.entities.Comentario;
import com.prateleira_inteligente.entities.Livro;
import com.prateleira_inteligente.entities.Usuario;
import com.prateleira_inteligente.repositories.ComentarioRepository;
import com.prateleira_inteligente.repositories.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final ComentarioRepository comentarioRepository;

    @Transactional
    public void delete(Livro livro) {
        // Remover a associação com categorias
        for (Categoria categoria : livro.getCategorias()) {
            categoria.getLivros().remove(livro);
        }
        livro.getCategorias().clear();  // Limpar a lista de categorias do livro

        // Remover a associação com usuários
        for (Usuario usuario : livro.getUsuarios()) {
            usuario.getLivros().remove(livro);
        }
        livro.getUsuarios().clear();  // Limpar a lista de usuários do livro

        // Remover comentários relacionados ao livro
        for (Comentario comentario : livro.getComentarios()) {
            if (comentario.getUsuario() != null) {
                comentario.getUsuario().getComentarios().remove(comentario);
            }
            comentarioRepository.delete(comentario);
        }
        livro.getComentarios().clear();  // Limpar a lista de comentários do livro

        // Por fim, remover o livro
        livroRepository.delete(livro);
    }

    @Transactional(readOnly = true)
    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Livro> findAllById(List<Long> ids){
        return livroRepository.findAllById(ids);
    }
}
