package com.prateleira_inteligente.app;

import com.prateleira_inteligente.persistence.GenericDAO;
import com.prateleira_inteligente.entities.Categoria;
import com.prateleira_inteligente.entities.Livro;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.Arrays;

public class TestDAO {

    @Autowired
    private GenericDAO<Categoria> categoriaDAO;  // Injeção do DAO de Categoria

    @Autowired
    private GenericDAO<Livro> livroDAO;  // Injeção do DAO de Livro

    public void testDAO() {
        // Criar instâncias de Categoria
        Categoria categoria1 = new Categoria();
        categoria1.setNome("Ficção Científica");

        Categoria categoria2 = new Categoria();
        categoria2.setNome("Fantasia");

        // Persistir categorias
        categoriaDAO.salvar(categoria1);
        categoriaDAO.salvar(categoria2);

        // Criar instâncias de Livro
        Livro livro1 = new Livro();
        livro1.setTitulo("Duna");
        livro1.setAutor("Frank Herbert");
        livro1.setAnoPublicacao(Date.valueOf("1965-06-01"));
        livro1.setDescricao("Uma obra-prima da ficção científica.");
        livro1.setEditora("Chilton Books");

        Livro livro2 = new Livro();
        livro2.setTitulo("O Senhor dos Anéis");
        livro2.setAutor("J.R.R. Tolkien");
        livro2.setAnoPublicacao(Date.valueOf("1954-07-29"));
        livro2.setDescricao("Uma das maiores aventuras de fantasia.");
        livro2.setEditora("George Allen & Unwin");

        // Configurar associações entre Livros e Categorias
        livro1.getCategorias().add(categoria1);
        livro2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));

        categoria1.getLivros().addAll(Arrays.asList(livro1, livro2));
        categoria2.getLivros().add(livro2);

        // Persistir livros
        livroDAO.salvar(livro1);
        livroDAO.salvar(livro2);

        // Exibir informações para verificar a configuração
        System.out.println("Livros e suas categorias:");
        for (Livro livro : Arrays.asList(livro1, livro2)) {
            System.out.println("Livro: " + livro.getTitulo());
            System.out.println("Categorias: ");
            livro.getCategorias().forEach(c -> System.out.println(" - " + c.getNome()));
        }

        System.out.println("\nCategorias e seus livros:");
        for (Categoria categoria : Arrays.asList(categoria1, categoria2)) {
            System.out.println("Categoria: " + categoria.getNome());
            System.out.println("Livros: ");
            categoria.getLivros().forEach(l -> System.out.println(" - " + l.getTitulo()));
        }
    }
}