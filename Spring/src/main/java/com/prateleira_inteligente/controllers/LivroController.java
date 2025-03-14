package com.prateleira_inteligente.controllers;

import com.prateleira_inteligente.dto.LivroDTO;
import com.prateleira_inteligente.entities.Livro;
import com.prateleira_inteligente.mappers.IMapper;
import com.prateleira_inteligente.services.IService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/livro")
public class LivroController extends AbsGenericController <Livro, LivroDTO> {

    public LivroController(IService<Livro> service, IMapper<Livro, LivroDTO> mapper) {
        super(service, mapper);
    }
}
