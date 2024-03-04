package com.cydeo.service;

import com.cydeo.model.Comment;
import com.cydeo.proxy.CommentNotificationProxy;
import com.cydeo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentService {

    // these are being injected in this class 2 dependency classes we make them final as being immutable/unchanged.
    private final CommentRepository commentRepository; // this is dependency
    private final CommentNotificationProxy commentNotificationProxy;  // this is dependency

    // arg constructor that takes 2 interfaces -> referencing object classes
    // @Autowired -> we use it when this class has 2 or more constructors
    public CommentService(CommentRepository commentRepository, CommentNotificationProxy commentNotificationProxy) {
        this.commentRepository = commentRepository;
        this.commentNotificationProxy = commentNotificationProxy;
    }


    // method to store and send comment
    public void publishComment(Comment comment){
        commentRepository.storeComment(comment);
        commentNotificationProxy.sendComment(comment);
    }


}
