package com.assessment.comsc.moderaionPanelComments;
import java.util.List;

//interface contract for save a comment and fetch record for data population
public interface CommentRepo {
    Comment save(Comment comment);

    List<Comment> getAllComments(int assessmentId);
}