package com.prateleira_inteligente.services;

import com.prateleira_inteligente.entities.Autor;
import com.prateleira_inteligente.entities.Livro;
import com.prateleira_inteligente.repositories.AutorRepository;
import com.prateleira_inteligente.repositories.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutorService implements IService<Autor> {

    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;

    @Override
    @Transactional
    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    @Transactional
    public Autor update(Long id, Autor autor) {
        return autorRepository.findById(id)
                .map(autorExistente -> {
                    // Atualiza o nome do autor
                    autorExistente.setNome(autor.getNome());

                    // Atualiza a lista de livros associada ao autor
                    List<Livro> novosLivros = livroRepository.findAllById(autor.getLivros().stream().map(Livro::getId).collect(Collectors.toList()));

                    // Atualiza o relacionamento bidirecional: associa o autor aos livros
                    for (Livro livro : novosLivros) {
                        livro.setAutor(autorExistente); // A linha que define o autor do livro
                    }

                    // Verificando se todos os livros estão sendo recuperados corretamente
                    System.out.println("Livros encontrados: " + novosLivros.stream().map(Livro::getId).collect(Collectors.toList()));

                    // Aqui é importante garantir que a lista de livros seja atualizada corretamente
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