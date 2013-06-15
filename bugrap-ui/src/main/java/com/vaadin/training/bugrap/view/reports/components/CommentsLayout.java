package com.vaadin.training.bugrap.view.reports.components;

import com.vaadin.training.bugrap.domain.entity.Comment;
import com.vaadin.training.bugrap.view.reports.ReportsPresenter;
import com.vaadin.ui.*;

import java.util.List;

public class CommentsLayout extends VerticalLayout {

    private ReportsPresenter presenter;

    public void setPresenter(ReportsPresenter presenter) {
        this.presenter = presenter;
    }

    private final Label commentsLabel;
    private TextArea commentTextArea;
    private final VerticalLayout commentsLayout;

    public CommentsLayout() {
        setSpacing(true);

        commentsLabel = new Label("Comments:");
        commentsLabel.setVisible(false);
        addComponent(commentsLabel);

        commentsLayout = new VerticalLayout();
        addComponent(commentsLayout);

        commentTextArea = new TextArea();
        commentTextArea.setInputPrompt("Write a new comment...");
        commentTextArea.setWidth("100%");
        commentTextArea.setHeight("150px");
        addComponent(commentTextArea);

        Button addCommentButton = new Button("Done", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                presenter.commentAdded(commentTextArea.getValue());
            }
        });
        addComponent(addCommentButton);
    }

    public void showComments(List<Comment> comments) {
        commentsLayout.removeAllComponents();

        if(comments.size() > 0) {
            commentsLabel.setVisible(true);
        }

        for (Comment comment : comments) {
            commentsLayout.addComponent(new CommentEntry(comment));
        }
    }

    class CommentEntry extends VerticalLayout {
        CommentEntry(Comment comment) {
            setSpacing(true);
            setMargin(true);
            Label commentHeaderLabel = new Label(comment.getTimestamp() + ". " + comment.getAuthor().getName() + " wrote: ");
            addComponent(commentHeaderLabel);
            Label commentTextLabel = new Label(comment.getComment());
            addComponent(commentTextLabel);
        }
    }
}
