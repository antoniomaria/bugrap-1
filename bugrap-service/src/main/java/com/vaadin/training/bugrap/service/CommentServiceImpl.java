package com.vaadin.training.bugrap.service;

import com.vaadin.training.bugrap.domain.entity.Comment;
import com.vaadin.training.bugrap.domain.repository.CommentRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
public class CommentServiceImpl implements CommentService {

    @Inject
    CommentRepository commentRepository;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
