package com.samirvaliyev.msmessage.controller;

import com.samirvaliyev.msmessage.model.dto.SaveTagDto;
import com.samirvaliyev.msmessage.model.dto.TagDto;
import com.samirvaliyev.msmessage.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveTag(@RequestBody SaveTagDto dto) {
        tagService.saveTag(dto);
    }

    @GetMapping
    public List<TagDto> getTags() {
        return tagService.getTags();
    }

}
