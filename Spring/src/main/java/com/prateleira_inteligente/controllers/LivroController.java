package com.prateleira_inteligente.controllers;

import com.prateleira_inteligente.dto.LivroDTO;
import com.prateleira_inteligente.services.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/livro")
public class LivroController {

    private final LivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll() {
        List<LivroDTO> livroDTOList = livroService.findAll()
                .stream()
                .map(LivroDTO::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(livroDTOList);
    }
}
