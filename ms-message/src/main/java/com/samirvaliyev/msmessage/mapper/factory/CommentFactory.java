package com.samirvaliyev.msmessage.mapper.factory;

import com.samirvaliyev.msmessage.dao.entity.CommentEntity;
import com.samirvaliyev.msmessage.dao.entity.PostEntity;

public class CommentFactory {

    public static CommentEntity buildCommentEntity(PostEntity post, String content) {
        return CommentEntity.builder()
                .post(post)
                .content(content)
                .build();
    }
}
