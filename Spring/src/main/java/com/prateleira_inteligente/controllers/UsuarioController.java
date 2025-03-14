package com.prateleira_inteligente.controllers;

import com.prateleira_inteligente.dto.UsuarioDTO;
import com.prateleira_inteligente.entities.Usuario;
import com.prateleira_inteligente.mappers.IMapper;
import com.prateleira_inteligente.services.IService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController extends AbsGenericController<Usuario, UsuarioDTO> {

    public UsuarioController(IService<Usuario> service, IMapper<Usuario, UsuarioDTO> mapper) {
        super(service, mapper);
    }
}