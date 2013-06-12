package com.vaadin.training.bugrap.domain.repository;

import com.vaadin.training.bugrap.domain.entity.User;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class UserRepository extends AbstractRepository<User> {
    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
