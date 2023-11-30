package com.samirvaliyev.msmessage.mapper.factory;

import com.samirvaliyev.msmessage.dao.entity.PostEntity;
import com.samirvaliyev.msmessage.model.dto.SavePostDto;

public class PostFactory {

    public static PostEntity buildPostEntity(SavePostDto dto) {
        return PostEntity.builder()
                .title(dto.getTitle())
                .build();
    }
}
