package com.prateleira_inteligente.services;

import com.prateleira_inteligente.entities.Categoria;
import com.prateleira_inteligente.entities.Comentario;
import com.prateleira_inteligente.entities.Livro;
import com.prateleira_inteligente.entities.Usuario;
import com.prateleira_inteligente.repositories.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LivroService implements IService<Livro>{

    private final LivroRepository livroRepository;
    private final ComentarioService comentarioService;


    @Override
    @Transactional
    public Livro save(Livro livro) {
        return null;
    }

    @Override
    @Transactional
    //TODO: Implementar o update
    public Livro update(Long id, Livro livro) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Livro getById(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID:" + id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Livro livro = getById(id);
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
            comentarioService.delete(comentario.getId());
        }
        livro.getComentarios().clear();  // Limpar a lista de comentários do livro

        // Por fim, remover o livro
        livroRepository.delete(livro);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Livro> findAll() {
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Livro> findAllById(List<Long> ids) {
        return livroRepository.findAllById(ids);
    }
}
