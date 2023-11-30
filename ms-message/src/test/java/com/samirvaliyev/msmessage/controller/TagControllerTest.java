package com.samirvaliyev.msmessage.controller;

import com.samirvaliyev.msmessage.model.dto.SaveTagDto;
import com.samirvaliyev.msmessage.model.dto.TagDto;
import com.samirvaliyev.msmessage.service.TagService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.samirvaliyev.msmessage.properties.TestContants.TAG_CONTROLLER_PATH;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@WebMvcTest(controllers = TagController.class)
public class TagControllerTest {

    private static SaveTagDto saveTagDto;
    private static List<TagDto> tagDtos;
    private static TagDto tagDto;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

    @BeforeAll
    static void setUp() {
        saveTagDto = new SaveTagDto();
        saveTagDto.setName("Test");

        tagDto = new TagDto();
        tagDto.setName("Test");

        tagDtos = new ArrayList<>();
        tagDtos.add(0, tagDto);
        tagDtos.add(1, tagDto);
    }

    @Test
    void saveTag_Success() throws Exception {
        //when
        doNothing().when(tagService).saveTag(saveTagDto);

        //then
        mockMvc.perform(post(TAG_CONTROLLER_PATH)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(saveTagDto)))
                .andExpect(status().isCreated());

        verify(tagService, timeout(1)).saveTag(saveTagDto);
    }

    @Test
    void getTags_Success() throws Exception {
        //when
        when(tagService.getTags()).thenReturn(tagDtos);

        //then
        mockMvc.perform(get(TAG_CONTROLLER_PATH))
                .andExpect(jsonPath("$", hasSize(tagDtos.size())))
                .andExpect(status().isOk());

        verify(tagService, times(1)).getTags();
    }

}
