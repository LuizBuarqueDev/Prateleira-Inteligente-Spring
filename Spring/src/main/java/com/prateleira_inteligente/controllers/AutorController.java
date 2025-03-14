package com.prateleira_inteligente.controllers;

import com.prateleira_inteligente.dto.AutorDTO;
import com.prateleira_inteligente.entities.Autor;
import com.prateleira_inteligente.mappers.IMapper;
import com.prateleira_inteligente.services.IService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/autores")
public class AutorController extends AbsGenericController<Autor, AutorDTO> {

    public AutorController(IService<Autor> service, IMapper<Autor, AutorDTO> mapper) {
        super(service, mapper);
    }
}
