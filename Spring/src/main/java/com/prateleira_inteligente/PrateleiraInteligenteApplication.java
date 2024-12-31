package com.prateleira_inteligente;

import com.prateleira_inteligente.entities.Categoria;
import com.prateleira_inteligente.persistence.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrateleiraInteligenteApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public static void main(String[] args) {
        SpringApplication.run(PrateleiraInteligenteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Criar e salvar uma nova categoria
        Categoria categoria = new Categoria();
        categoria.setNome("Tecnologia");
                categoriaRepository.save(categoria);

        // Recuperar e exibir todas as categorias
        categoriaRepository.findAll().forEach(System.out::println);
    }
}
