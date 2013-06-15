package com.vaadin.training.bugrap.service;

import com.vaadin.training.bugrap.domain.entity.Comment;

import javax.ejb.Local;

@Local
public interface CommentService {
    Comment save(Comment comment);
}
