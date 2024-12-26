package com.prateleira_inteligente.persistence;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository  // Garantir que o Spring reconheça como um bean gerenciado
@Transactional
public class GenericDAO<T> implements IGenericDAO<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> classeEntidade;

    public GenericDAO(Class<T> classeEntidade) {
        this.classeEntidade = classeEntidade;
    }

    @Override
    public void salvar(T entidade) {
        entityManager.persist(entidade);
    }

    @Override
    public void atualizar(T entidade) {
        entityManager.merge(entidade);
    }

    @Override
    public T buscarPorId(Long id) {
        return entityManager.find(classeEntidade, id);
    }

    @Override
    public void excluirPorId(Long id) {
        T entidade = buscarPorId(id);
        if (entidade != null) {
            entityManager.remove(entidade);
        }
    }

    @Override
    public List<T> buscarTodos() {
        String consulta = "SELECT e FROM " + classeEntidade.getSimpleName() + " e";
        return entityManager.createQuery(consulta, classeEntidade).getResultList();
    }
}