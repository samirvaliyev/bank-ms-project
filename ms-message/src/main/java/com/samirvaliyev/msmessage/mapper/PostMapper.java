package com.samirvaliyev.msmessage.mapper;

import com.samirvaliyev.msmessage.dao.entity.PostEntity;
import com.samirvaliyev.msmessage.model.dto.PostDto;

public class PostMapper {

    public static PostDto mapEntityToDto(PostEntity entity) {
        return new PostDto(entity.getId(), entity.getTitle());
    }
}
