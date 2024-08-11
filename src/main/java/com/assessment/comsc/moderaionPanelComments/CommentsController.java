package com.assessment.comsc.moderaionPanelComments;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
public class CommentsController {
    private final CommentRepo commentRepo;

    private CommentsController(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    // Endpoint to render moderation panel comments
    @GetMapping("/moderationComments")
    public String view() {
        return "moderationPanel";
    }

    // Endpoint to fetch record based on assessment id
    @GetMapping("/getComments")
    @ResponseBody
    public List<Comment> getComments(@RequestParam("assessmentId") int assessmentId) {
        return commentRepo.getAllComments(assessmentId);
    }

    // Endpoint to save a new record
    @PostMapping("/saveComments")
    public String save(@RequestBody Comment comment) {
        commentRepo.save(comment);

        return "moderationPanel";
    }
}
