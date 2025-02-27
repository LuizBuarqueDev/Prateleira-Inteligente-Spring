package com.prateleira_inteligente.controllers;

import com.prateleira_inteligente.dto.ComentarioDTO;
import com.prateleira_inteligente.mapper.ComentarioMapper;
import com.prateleira_inteligente.services.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Comentario")
public class ComentarioController {

    private final ComentarioService comentarioService;
    private final ComentarioMapper comentarioMapper;

    @GetMapping
    public ResponseEntity<List<ComentarioDTO>> getComentarios(){
        List<ComentarioDTO> comentarioDTOList = comentarioService.findAll()
                .stream()
                .map(comentarioMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(comentarioDTOList);
    }
}
