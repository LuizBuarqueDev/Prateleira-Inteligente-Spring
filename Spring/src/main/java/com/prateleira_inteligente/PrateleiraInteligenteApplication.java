package com.prateleira_inteligente;

import com.prateleira_inteligente.entities.*;
import com.prateleira_inteligente.repositories.*;
import com.prateleira_inteligente.services.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;

@RequiredArgsConstructor
@SpringBootApplication
public class PrateleiraInteligenteApplication implements CommandLineRunner {

    private final LivroService livroService;
    private final UsuarioService usuarioService;
    private final CategoriaService categoriaService;
    private final AutorService autorService;
    private final ComentarioService comentarioService;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final AutorRepository autorRepository;
    private final ComentarioRepository comentarioRepository;
    private final AvaliacaoRepository avaliacaoRepository;

    public static void main(String[] args) {
        SpringApplication.run(PrateleiraInteligenteApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) {
        // CRIAÇÃO DOS DADOS

        // Criar autores
        Autor autor1 = new Autor();
        autor1.setNome("Herbert Schildt");

        Autor autor2 = new Autor();
        autor2.setNome("Craig Walls");

        autorRepository.saveAll(Arrays.asList(autor1, autor2));

        // Criar livros
        Livro livro1 = new Livro();
        livro1.setTitulo("Java para Iniciantes");
        livro1.setAnoPublicacao(LocalDate.of(2020,1,1));
        livro1.setDescricao("Um guia completo para aprender Java.");
        livro1.setEditora("McGraw-Hill");
        livro1.setAutor(autor1);

        Livro livro2 = new Livro();
        livro2.setTitulo("Spring Framework");
        livro2.setAnoPublicacao(LocalDate.of(2019,3,15));
        livro2.setDescricao("Explorando os recursos do Spring.");
        livro2.setEditora("Packt Publishing");
        livro2.setAutor(autor2);

        // Criar categorias
        Categoria categoriaTecnologia = new Categoria();
        categoriaTecnologia.setNome("Tecnologia");

        Categoria categoriaProgramacao = new Categoria();
        categoriaProgramacao.setNome("Programação");

        // Estabelecer relacionamento Livro-Categoria
        livro1.getCategorias().addAll(Arrays.asList(categoriaTecnologia, categoriaProgramacao));
        livro2.getCategorias().add(categoriaTecnologia);

        categoriaTecnologia.getLivros().addAll(Arrays.asList(livro1, livro2));
        categoriaProgramacao.getLivros().add(livro1);

        // Criar usuários
        Usuario usuario1 = new Usuario();
        usuario1.setNome("João Silva");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Maria Oliveira");

        // Estabelecer relacionamento Usuario-Livro
        usuario1.getLivros().add(livro1);
        livro1.getUsuarios().add(usuario1);

        usuario2.getLivros().add(livro2);
        livro2.getUsuarios().add(usuario2);

        // Criar comentários
        Comentario comentario1 = new Comentario();
        comentario1.setUsuario(usuario1);
        comentario1.setLivro(livro1);
        comentario1.setTexto("Ótimo livro para iniciantes!");

        Comentario comentario2 = new Comentario();
        comentario2.setUsuario(usuario2);
        comentario2.setLivro(livro2);
        comentario2.setTexto("Aprendi muito sobre Spring!");

        livro1.getComentarios().add(comentario1);
        usuario1.getComentarios().add(comentario1);

        livro2.getComentarios().add(comentario2);
        usuario2.getComentarios().add(comentario2);

        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setUsuario(usuario1);
        avaliacao1.setLivro(livro1);
        avaliacao1.setNota(10.0);

        // Salvar as entidades
        categoriaRepository.saveAll(Arrays.asList(categoriaTecnologia, categoriaProgramacao));
        livroRepository.saveAll(Arrays.asList(livro1, livro2));
        usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2));
        comentarioRepository.saveAll(Arrays.asList(comentario1, comentario2));
        avaliacaoRepository.save(avaliacao1);

        // Exibir dados antes da remoção
        System.out.println("=== Dados Antes da Remoção ===");
        listarDados();

        // TESTE DOS SERVICES
        listarDados();

        // TESTE DE REMOÇÃO

        // Remover um comentário
        System.out.println("\n=== Remover um comentário ===");
        comentarioRepository.delete(comentario1);
        comentarioRepository.delete(comentario2);
        listarDados();

        // Remover uma avaliação
        System.out.println("\n=== Remover uma avaliação ===");
        avaliacaoRepository.delete(avaliacao1);
        listarDados();

        // Remover um livro
        System.out.println("\n=== Remover um livro ===");
        livroRepository.delete(livro1);
        livroRepository.delete(livro2);
        listarDados();

        // Remover um autor
        System.out.println("\n=== Remover um autor ===");
        autorRepository.delete(autor1);
        autorRepository.delete(autor2);
        listarDados();

        // Remover um usuário
        System.out.println("\n=== Remover um usuário ===");
        usuarioRepository.delete(usuario1);
        usuarioRepository.delete(usuario2);
        listarDados();

        // Remover uma categoria
        System.out.println("\n=== Remover uma categoria ===");
        categoriaRepository.delete(categoriaTecnologia);
        categoriaRepository.delete(categoriaProgramacao);
        listarDados();
    }

    private void listarDados() {
        System.out.println("Avaliações e seus livros");
        avaliacaoRepository.findAll().forEach(avaliacao -> {
            System.out.println("Avaliação"  + avaliacao.getNota());
            System.out.println("Livro avaliacao:  " + avaliacao.getLivro().getTitulo());
        });


        System.out.println("Categorias e seus livros:");
        categoriaRepository.findAll().forEach(categoria -> {
            System.out.println("Categoria: " + categoria.getNome());
            categoria.getLivros().forEach(livro -> System.out.println("  - Livro: " + livro.getTitulo()));
        });

        System.out.println("\nUsuários e seus livros:");
        usuarioRepository.findAll().forEach(usuario -> {
            System.out.println("Usuário: " + usuario.getNome());
            usuario.getLivros().forEach(livro -> System.out.println("  - Livro: " + livro.getTitulo()));
        });

        System.out.println("\nAutores e seus livros:");
        autorRepository.findAll().forEach(autor -> {
            System.out.println("Autor: " + autor.getNome());
            autor.getLivros().forEach(livro -> System.out.println("  - Livro: " + livro.getTitulo()));
        });

        System.out.println("\nComentários e seus dados:");
        comentarioRepository.findAll().forEach(comentario -> {
            System.out.println("Comentário ID: " + comentario.getId());
            System.out.println("  - Usuário: " + comentario.getUsuario().getNome());
            System.out.println("  - Livro: " + comentario.getLivro().getTitulo());
            System.out.println("  - Texto: " + comentario.getTexto());
        });

        System.out.println("\nLivros restantes:");
        livroRepository.findAll().forEach(livro -> System.out.println("Livro: " + livro.getTitulo()));
    }
}