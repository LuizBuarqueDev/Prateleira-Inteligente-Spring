package com.prateleira_inteligente.persistence;

import java.util.List;

public interface IGenericDAO<T> {

    void salvar(T entidade);

    void atualizar(T entidade);

    T buscarPorId(Long id);

    void excluirPorId(Long id);

    List<T> buscarTodos();
}
