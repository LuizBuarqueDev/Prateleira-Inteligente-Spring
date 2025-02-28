package com.prateleira_inteligente.controllers;

import com.prateleira_inteligente.dto.UsuarioDTO;
import com.prateleira_inteligente.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> usuarioDTOList = usuarioService.findAll()
                .stream()
                .map(UsuarioDTO::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarioDTOList);
    }
}
