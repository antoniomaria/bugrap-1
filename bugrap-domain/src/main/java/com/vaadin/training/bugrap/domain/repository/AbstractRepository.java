package com.vaadin.training.bugrap.domain.repository;

import com.vaadin.training.bugrap.domain.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public abstract class AbstractRepository<E extends AbstractEntity> {

    @PersistenceContext
    EntityManager em;

    protected abstract Class<E> getEntityClass();

    public E find(Long id) {
        return em.find(getEntityClass(), id);
    }

    public List<E> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
        criteriaQuery.from(getEntityClass());

        return em.createQuery(criteriaQuery).getResultList();
    }

    public E save(E entity) {
        E saved;
        if (entity.isPersistent()) {
            saved = em.merge(entity);
        } else {
            em.persist(entity);
            saved = entity;
        }
        return saved;
    }
}
