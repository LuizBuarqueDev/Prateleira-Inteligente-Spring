package com.prateleira_inteligente.services;

import com.prateleira_inteligente.entities.Autor;
import com.prateleira_inteligente.entities.Livro;
import com.prateleira_inteligente.repositories.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutorService implements IService<Autor> {

    private final AutorRepository autorRepository;
    private final LivroService livroService;

    @Override
    @Transactional
    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    @Transactional
    public Autor update(Long id, Autor autor) {
        //TODO: Consertar, quando um livro e atualizado os autores que não foram atualizados perdem seus livros.
        return autorRepository.findById(id)
                .map(autorExistente -> {
                    // Atualiza o nome do autor
                    autorExistente.setNome(autor.getNome());

                    // Desassocia os livros antigos
                    for (Livro livro : autorExistente.getLivros()) {
                        livro.setAutor(null);
                    }

                    // Recupera os novos livros
                    List<Livro> novosLivros = livroService.findAllById(
                            autor.getLivros().stream().map(Livro::getId).collect(Collectors.toList())
                    );

                    // Verifica se o livro já tem um autor e remove a referência anterior
                    for (Livro livro : novosLivros) {
                        if (livro.getAutor() != null && !livro.getAutor().equals(autorExistente)) {
                            livro.getAutor().getLivros().remove(livro);
                        }
                        livro.setAutor(autorExistente);
                    }


                    // Atualiza a lista de livros do autor
                    autorExistente.setLivros(novosLivros);

                    // Salva e retorna o autor atualizado
                    return autorRepository.save(autorExistente);
                })
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Autor getById(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID:" + id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        autorRepository.delete(getById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }
}