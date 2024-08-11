package com.assessment.comsc.feedbackform;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PostFeedbackRepository {
    PostFeedback savePostFeedback(PostFeedback assessment);

    @Select("SELECT AVG(score) AS averageScore FROM assessmentfeedback")
    Double calculateAverageScore();
}
