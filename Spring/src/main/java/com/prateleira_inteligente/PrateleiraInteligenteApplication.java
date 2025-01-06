package com.prateleira_inteligente;

import com.prateleira_inteligente.entities.*;
import com.prateleira_inteligente.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.util.Arrays;

@SpringBootApplication
public class PrateleiraInteligenteApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public static void main(String[] args) {
        SpringApplication.run(PrateleiraInteligenteApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Criar livros
        Livro livro1 = new Livro();
        livro1.setTitulo("Java para Iniciantes");
        livro1.setAutor("Herbert Schildt");
        livro1.setAnoPublicacao(Date.valueOf("2020-01-01"));
        livro1.setDescricao("Um guia completo para aprender Java.");
        livro1.setEditora("McGraw-Hill");

        Livro livro2 = new Livro();
        livro2.setTitulo("Spring Framework");
        livro2.setAutor("Craig Walls");
        livro2.setAnoPublicacao(Date.valueOf("2019-03-15"));
        livro2.setDescricao("Explorando os recursos do Spring.");
        livro2.setEditora("Packt Publishing");

        // Criar categorias
        Categoria categoriaTecnologia = new Categoria();
        categoriaTecnologia.setNome("Tecnologia");

        Categoria categoriaProgramacao = new Categoria();
        categoriaProgramacao.setNome("Programação");

        // Estabelecer relacionamento Livro-Categoria
        livro1.getCategorias().add(categoriaTecnologia);
        livro1.getCategorias().add(categoriaProgramacao);

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
        usuario2.getLivros().add(livro2);

        livro1.getUsuarios().add(usuario1);
        livro2.getUsuarios().add(usuario2);

        // Criar comentários
        Comentario comentario1 = new Comentario();
        comentario1.setUsuario(usuario1); // Associar o comentário ao usuário João Silva

        Comentario comentario2 = new Comentario();
        comentario2.setUsuario(usuario2); // Associar o comentário ao usuário Maria Oliveira

        // Relacionar comentários a usuários
        usuario1.getComentarios().add(comentario1);
        usuario2.getComentarios().add(comentario2);

        // Salvar entidades
        categoriaRepository.saveAll(Arrays.asList(categoriaTecnologia, categoriaProgramacao));
        livroRepository.saveAll(Arrays.asList(livro1, livro2));
        usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2));
        comentarioRepository.saveAll(Arrays.asList(comentario1, comentario2));

        // Recuperar e exibir dados
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

        System.out.println("\nComentários e seus autores:");
        comentarioRepository.findAll().forEach(comentario -> {
            System.out.println("Comentário ID: " + comentario.getId());
            System.out.println("  - Autor: " + comentario.getUsuario().getNome());
        });
    }
}