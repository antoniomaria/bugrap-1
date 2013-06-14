package com.vaadin.training.bugrap.domain.repository;

import javax.persistence.TypedQuery;

import com.vaadin.training.bugrap.domain.entity.User;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class UserRepository extends AbstractRepository<User> {

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }


    public User findByUserName(String userName) {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_USERNAME, User.class);
        query.setParameter("username", userName);

        return getSingleResultOrNull(query);
    }

}
