package com.prateleira_inteligente.services;

import com.prateleira_inteligente.entities.Comentario;
import com.prateleira_inteligente.entities.Livro;
import com.prateleira_inteligente.entities.Usuario;
import com.prateleira_inteligente.repositories.ComentarioRepository;
import com.prateleira_inteligente.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ComentarioRepository comentarioRepository;
    
    @Transactional
    public void deleteUsuario(Usuario usuario) {
        // Remover o usuário da associação com livros
        for (Livro livro : usuario.getLivros()) {
            livro.getUsuarios().remove(usuario);
        }
        usuario.getLivros().clear();

        // Remover os comentários feitos pelo usuário
        for (Comentario comentario : usuario.getComentarios()) {
            if (comentario.getLivro() != null) {
                comentario.getLivro().getComentarios().remove(comentario);
            }
            comentarioRepository.delete(comentario);
        }
        usuario.getComentarios().clear();

        usuarioRepository.delete(usuario);
    }
}
