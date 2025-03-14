package com.prateleira_inteligente.controllers;

import com.prateleira_inteligente.dto.CategoriaDTO;
import com.prateleira_inteligente.entities.Categoria;
import com.prateleira_inteligente.mappers.IMapper;
import com.prateleira_inteligente.services.IService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController extends AbsGenericController<Categoria, CategoriaDTO> {

    public CategoriaController(IService<Categoria> service, IMapper<Categoria, CategoriaDTO> mapper) {
        super(service, mapper);
    }
}