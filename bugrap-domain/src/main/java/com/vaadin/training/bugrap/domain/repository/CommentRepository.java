package com.vaadin.training.bugrap.domain.repository;

import com.vaadin.training.bugrap.domain.entity.Comment;

public class CommentRepository extends AbstractRepository<Comment>{

    @Override
    protected Class<Comment> getEntityClass() {
        return Comment.class;
    }
}
