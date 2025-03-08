package com.prateleira_inteligente.services;

import com.prateleira_inteligente.entities.Comentario;
import com.prateleira_inteligente.entities.Livro;
import com.prateleira_inteligente.entities.Usuario;
import com.prateleira_inteligente.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService  implements IService<Usuario>{

    private final UsuarioRepository usuarioRepository;
    private final ComentarioService comentarioService;

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    //TODO: Fazer o metodo update
    public Usuario update(Long id, Usuario usuario) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID:" + id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Usuario usuario = getById(id);
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
            comentarioService.delete(comentario.getId());
        }
        usuario.getComentarios().clear();

        usuarioRepository.delete(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAllById(List<Long> ids) {
        return usuarioRepository.findAllById(ids);
    }
}
