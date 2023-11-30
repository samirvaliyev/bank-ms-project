package com.samirvaliyev.msmessage.mapper.factory;

import com.samirvaliyev.msmessage.dao.entity.PostDetailEntity;
import com.samirvaliyev.msmessage.dao.entity.PostEntity;

public class PostDetailFactory {

    public static PostDetailEntity buildPostDetailEntity(PostEntity post, String createdBy) {
        return PostDetailEntity.builder()
                .post(post)
                .createdBy(createdBy)
                .build();
    }
}
