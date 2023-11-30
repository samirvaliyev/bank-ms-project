package com.samirvaliyev.msmessage.controller;

import com.samirvaliyev.msmessage.model.dto.CommentDto;
import com.samirvaliyev.msmessage.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PatchMapping("/{id}/view-status")
    @ResponseStatus(NO_CONTENT)
    public void changeCommentViewStatus(@PathVariable Long id) {
        commentService.changeCommentViewStatus(id);
    }

    @GetMapping("/posts/{postId}")
    public List<CommentDto> getCommentsByPost(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }

    @PatchMapping("/{id}/content")
    @ResponseStatus(NO_CONTENT)
    public void changeCommentContent(@PathVariable Long id, @RequestParam String value) {
        commentService.changeCommentContent(id, value);
    }
}
