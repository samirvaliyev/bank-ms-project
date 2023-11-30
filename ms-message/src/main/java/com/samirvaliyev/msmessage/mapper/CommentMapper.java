package com.samirvaliyev.msmessage.mapper;

import com.samirvaliyev.msmessage.dao.entity.CommentEntity;
import com.samirvaliyev.msmessage.model.dto.CommentDto;

public class CommentMapper {

    public static CommentDto mapEntityToDto(CommentEntity entity) {
        return CommentDto.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .viewed(entity.isViewed())
                .build();
    }
}
