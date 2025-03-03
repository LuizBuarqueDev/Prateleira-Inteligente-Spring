package com.prateleira_inteligente.controllers;

import com.prateleira_inteligente.dto.AutorDTO;
import com.prateleira_inteligente.entities.Autor;
import com.prateleira_inteligente.services.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/autores")
public class AutorController {

    private final AutorService autorService;

    // Criar um novo autor
    @PostMapping("/create")
    public ResponseEntity<AutorDTO> create(@RequestBody AutorDTO autorDTO) {
        Autor novoAutor = autorService.save(autorDTO.toEntity());
        return ResponseEntity.ok(AutorDTO.toDTO(novoAutor));
    }

    // Buscar autor por ID
    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> findById(@PathVariable Long id) {
        Autor autor = autorService.getById(id);
        return ResponseEntity.ok(AutorDTO.toDTO(autor));
    }


    // Atualizar um autor existente
    @PutMapping("/update/{id}")
    public ResponseEntity<AutorDTO> update(@PathVariable Long id, @RequestBody AutorDTO autorDTO) {
        Autor autorAtualizado = autorService.update(id, autorDTO.toEntity());
        return ResponseEntity.ok(AutorDTO.toDTO(autorAtualizado));
    }

    // Excluir um autor por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        autorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar todos os autores
    @GetMapping
    public ResponseEntity<List<AutorDTO>> findAll() {
        List<AutorDTO> autorDTOList = autorService.findAll()
                .stream()
                .map(AutorDTO::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(autorDTOList);
    }
}
