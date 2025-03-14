package com.prateleira_inteligente.controllers;

import com.prateleira_inteligente.dto.ComentarioDTO;
import com.prateleira_inteligente.entities.Comentario;
import com.prateleira_inteligente.mappers.IMapper;
import com.prateleira_inteligente.services.IService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comentario")
public class ComentarioController extends AbsGenericController<Comentario, ComentarioDTO> {

    public ComentarioController(IService<Comentario> service, IMapper<Comentario, ComentarioDTO> mapper) {
        super(service, mapper);
    }
}
