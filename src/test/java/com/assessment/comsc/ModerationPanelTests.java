package com.assessment.comsc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.assessment.comsc.moderaionPanelComments.Comment;
import com.assessment.comsc.moderaionPanelComments.CommentsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.List;

@SpringBootTest
public class ModerationPanelTests {

    @Autowired
    private CommentsController commentsController;
    private static Integer assessmentId;

    @BeforeAll
    public static void before() {
        assessmentId = 1;
    }

    @Test
    public void sendEmailSuccess() {
        System.out.println("Entering get comment unit testing");

        List<Comment> comment = commentsController.getComments(assessmentId);

        // Check if the comment is not null
        assertNotNull(comment);

        // Check the size of the comment if it is not recieving records more than 1
        assertEquals(1, comment.size());

        // Print the contents of each comment
        for (Comment value : comment) {
            System.out.println(value);
        }

        System.out.println("Exiting get comment unit testing");
    }

}
