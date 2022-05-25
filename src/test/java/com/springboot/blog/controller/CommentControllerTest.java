package com.springboot.blog.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CommentController.class})
@ExtendWith(SpringExtension.class)
class CommentControllerTest {
    @Autowired
    private CommentController commentController;

    @MockBean
    private CommentService commentService;

    /**
     * Method under test: {@link CommentController#createComment(long, CommentDto)}
     */
    @Test
    void testCreateComment() throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setBody("Not all who wander are lost");
        commentDto.setEmail("jane.doe@example.org");
        commentDto.setId(123L);
        commentDto.setName("Name");
        when(this.commentService.createComment(anyLong(), (CommentDto) any())).thenReturn(commentDto);

        CommentDto commentDto1 = new CommentDto();
        commentDto1.setBody("Not all who wander are lost");
        commentDto1.setEmail("jane.doe@example.org");
        commentDto1.setId(123L);
        commentDto1.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(commentDto1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/posts/{postId}/comments", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"body\":\"Not all who wander are lost\"}"));
    }

    /**
     * Method under test: {@link CommentController#getCommentsByPostId(Long)}
     */
    @Test
    void testGetCommentsByPostId() throws Exception {
        when(this.commentService.getCommentsByPostId(anyLong())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/posts/{postId}/comments", 123L);
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CommentController#getCommentsByPostId(Long)}
     */
    @Test
    void testGetCommentsByPostId2() throws Exception {
        when(this.commentService.getCommentsByPostId(anyLong())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/posts/{postId}/comments", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CommentController#getCommentById(Long, Long)}
     */
    @Test
    void testGetCommentById() throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setBody("Not all who wander are lost");
        commentDto.setEmail("jane.doe@example.org");
        commentDto.setId(123L);
        commentDto.setName("Name");
        when(this.commentService.getCommentById((Long) any(), (Long) any())).thenReturn(commentDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/posts/{postId}/comments/{id}",
                123L, 123L);
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"body\":\"Not all who wander are lost\"}"));
    }

    /**
     * Method under test: {@link CommentController#getCommentById(Long, Long)}
     */
    @Test
    void testGetCommentById2() throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setBody("Not all who wander are lost");
        commentDto.setEmail("jane.doe@example.org");
        commentDto.setId(123L);
        commentDto.setName("Name");
        when(this.commentService.getCommentById((Long) any(), (Long) any())).thenReturn(commentDto);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/posts/{postId}/comments/{id}", 123L,
                123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"body\":\"Not all who wander are lost\"}"));
    }

    /**
     * Method under test: {@link CommentController#updateComment(Long, Long, CommentDto)}
     */
    @Test
    void testUpdateComment() throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setBody("Not all who wander are lost");
        commentDto.setEmail("jane.doe@example.org");
        commentDto.setId(123L);
        commentDto.setName("Name");
        when(this.commentService.updateComment((Long) any(), anyLong(), (CommentDto) any())).thenReturn(commentDto);

        CommentDto commentDto1 = new CommentDto();
        commentDto1.setBody("Not all who wander are lost");
        commentDto1.setEmail("jane.doe@example.org");
        commentDto1.setId(123L);
        commentDto1.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(commentDto1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/posts/{postId}/comments/{id}", 123L, 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"body\":\"Not all who wander are lost\"}"));
    }

    /**
     * Method under test: {@link CommentController#deleteComment(Long, Long)}
     */
    @Test
    void testDeleteComment() throws Exception {
        doNothing().when(this.commentService).deleteComment((Long) any(), (Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/posts/{postId}/comments/{commentId}", 123L, 123L);
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Comment deleted successfully"));
    }

    /**
     * Method under test: {@link CommentController#deleteComment(Long, Long)}
     */
    @Test
    void testDeleteComment2() throws Exception {
        doNothing().when(this.commentService).deleteComment((Long) any(), (Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders
                .delete("/api/v1/posts/{postId}/comments/{commentId}", 123L, 123L);
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Comment deleted successfully"));
    }
}

