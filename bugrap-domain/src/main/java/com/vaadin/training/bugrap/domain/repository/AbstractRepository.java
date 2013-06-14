package com.vaadin.training.bugrap.domain.repository;

import com.vaadin.training.bugrap.domain.entity.AbstractEntity;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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


    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public E save(E entity) {
        E saved;
        if (entity.isPersistent()) {
            saved = em.merge(entity);
        } else {
            em.persist(entity);
            saved = entity;
        }
        em.flush();
        return saved;
    }


    protected E getSingleResultOrNull(TypedQuery<E> query) {
        E foundEntity = null;

        try {
            foundEntity = query.getSingleResult();
        } catch (NoResultException derp) {
            // herp derp jpa derp
        }

        return foundEntity;
    }
}
