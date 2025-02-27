package com.prateleira_inteligente.controllers;

import com.prateleira_inteligente.dto.AvaliacaoDTO;
import com.prateleira_inteligente.entities.Avaliacao;
import com.prateleira_inteligente.mapper.AvaliacaoMapper;
import com.prateleira_inteligente.services.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;
    private final AvaliacaoMapper avaliacaoMapper;

    @GetMapping
    public ResponseEntity<List<AvaliacaoDTO>> findAll() {
        List<AvaliacaoDTO> avaliacoes = avaliacaoService.findAll()
                .stream()
                .map(avaliacaoMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(avaliacoes);
    }
}