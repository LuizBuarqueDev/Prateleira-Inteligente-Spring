package com.prateleira_inteligente;

import com.prateleira_inteligente.entities.Categoria;
import com.prateleira_inteligente.entities.Livro;
import com.prateleira_inteligente.persistence.CategoriaRepository;
import com.prateleira_inteligente.persistence.LivroRepository;
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

        // Estabelecer relacionamento
        livro1.getCategorias().add(categoriaTecnologia);
        livro1.getCategorias().add(categoriaProgramacao);

        livro2.getCategorias().add(categoriaTecnologia);

        categoriaTecnologia.getLivros().add(livro1);
        categoriaTecnologia.getLivros().add(livro2);

        categoriaProgramacao.getLivros().add(livro1);

        // Salvar entidades
        livroRepository.save(livro1);
        livroRepository.save(livro2);
        categoriaRepository.save(categoriaTecnologia);
        categoriaRepository.save(categoriaProgramacao);

        // Recuperar e exibir categorias com livros
        categoriaRepository.findAll().forEach(categoria -> {
            System.out.println("Categoria: " + categoria.getNome());
            categoria.getLivros().forEach(livro ->
                    System.out.println("  - Livro: " + livro.getTitulo())
            );
        });
    }
}