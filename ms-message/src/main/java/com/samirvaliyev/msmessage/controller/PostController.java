package com.samirvaliyev.msmessage.controller;

import com.samirvaliyev.msmessage.model.dto.PostDto;
import com.samirvaliyev.msmessage.model.dto.SaveCommentDto;
import com.samirvaliyev.msmessage.model.dto.SavePostDto;
import com.samirvaliyev.msmessage.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void savePost(@RequestBody SavePostDto dto) {
        postService.savePost(dto);
    }

    @PatchMapping("/{id}/comments")
    @ResponseStatus(NO_CONTENT)
    public void addCommentToPost(@PathVariable Long id, @RequestBody SaveCommentDto dto) {
        postService.addCommentToPost(id, dto);
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }
}
