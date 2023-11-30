package com.samirvaliyev.msmessage.service;

import com.samirvaliyev.msmessage.dao.entity.PostEntity;
import com.samirvaliyev.msmessage.dao.repository.PostRepository;
import com.samirvaliyev.msmessage.exception.NotFoundException;
import com.samirvaliyev.msmessage.mapper.PostMapper;
import com.samirvaliyev.msmessage.mapper.factory.CommentFactory;
import com.samirvaliyev.msmessage.mapper.factory.PostDetailFactory;
import com.samirvaliyev.msmessage.mapper.factory.PostFactory;
import com.samirvaliyev.msmessage.model.dto.PostDto;
import com.samirvaliyev.msmessage.model.dto.SaveCommentDto;
import com.samirvaliyev.msmessage.model.dto.SavePostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.samirvaliyev.msmessage.model.constants.ExceptionConstants.POST_NOT_FOUND_CODE;
import static com.samirvaliyev.msmessage.model.constants.ExceptionConstants.POST_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final TagService tagService;

    public void savePost(SavePostDto request) {
      var post = PostFactory.buildPostEntity(request);
      var postDetail = PostDetailFactory.buildPostDetailEntity(post, request.getCreatedBy());

      if (!request.getTagIds().isEmpty()) {
          var tags = tagService.getTagsByIds(request.getTagIds());
          post.setTags(tags);
      }

      post.setDetail(postDetail);
      postRepository.save(post);
    }

    public void addCommentToPost(Long id, SaveCommentDto dto) {
        var post = fetchPostIfExist(id);
        var comments = post.getComments();

        comments.add(CommentFactory.buildCommentEntity(post, dto.getContent()));
        post.setComments(comments);

        postRepository.save(post);
    }

    public PostDto getPost(Long id) {
        var post = fetchPostIfExist(id);

        return PostMapper.mapEntityToDto(post);
    }

    private PostEntity fetchPostIfExist(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format(POST_NOT_FOUND_MESSAGE, id),
                        POST_NOT_FOUND_CODE));
    }
}
