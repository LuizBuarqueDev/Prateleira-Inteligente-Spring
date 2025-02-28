package com.prateleira_inteligente.controllers;

import com.prateleira_inteligente.dto.CategoriaDTO;
import com.prateleira_inteligente.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> categoriaDTOList = categoriaService.findAll()
                .stream()
                .map(CategoriaDTO::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoriaDTOList);
    }
}