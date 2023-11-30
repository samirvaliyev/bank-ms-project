package com.samirvaliyev.msmessage.controller;

import com.samirvaliyev.msmessage.model.dto.PostDto;
import com.samirvaliyev.msmessage.model.dto.SaveCommentDto;
import com.samirvaliyev.msmessage.model.dto.SavePostDto;
import com.samirvaliyev.msmessage.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.samirvaliyev.msmessage.properties.TestContants.POST_CONTROLLER_PATH;
import static com.samirvaliyev.msmessage.properties.TestContants.POST_ID;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@WebMvcTest(controllers = PostController.class)
class PostControllerTest {

    private static SavePostDto savePostDto;
    private static SaveCommentDto saveCommentDto;
    private static PostDto postDto;

    @MockBean
    private PostService postService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        savePostDto = new SavePostDto();
        savePostDto.setTitle("Post 1");

        saveCommentDto = new SaveCommentDto();
        saveCommentDto.setContent("Test Comment Content");

        postDto = new PostDto();
        postDto.setId(1L);
    }

    @Test
    void savePost_Success() throws Exception {
        //when
        doNothing().when(postService).savePost(savePostDto);

        //then
        mockMvc.perform(post(POST_CONTROLLER_PATH)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(savePostDto)))
                .andExpect(status().isCreated());

        verify(postService, times(1)).savePost(savePostDto);
    }

    @Test
    void addCommentToPost_Success() throws Exception {
        //when
        doNothing().when(postService).addCommentToPost(POST_ID, saveCommentDto);

        //then
        mockMvc.perform(patch(POST_CONTROLLER_PATH + "/{id}/comments", POST_ID)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saveCommentDto)))
                .andExpect(status().isNoContent());

        verify(postService, times(1)).addCommentToPost(POST_ID, saveCommentDto);
    }

    @Test
    void getPostById_Success() throws Exception {
        //when
        when(postService.getPost(POST_ID)).thenReturn(postDto);

        //then
        mockMvc.perform(get(POST_CONTROLLER_PATH + "/{id}", POST_ID))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(status().isOk());

        verify(postService, times(1)).getPost(POST_ID);
    }
}
