package com.samirvaliyev.msmessage.mapper;

import com.samirvaliyev.msmessage.dao.entity.TagEntity;
import com.samirvaliyev.msmessage.model.dto.SaveTagDto;
import com.samirvaliyev.msmessage.model.dto.TagDto;

public class TagMapper {

    public static TagEntity mapSaveTagRequestToEntity(SaveTagDto dto) {
        return TagEntity.builder()
                .name(dto.getName())
                .build();
    }

    public static TagDto mapEntityToDto(TagEntity entity) {
        return TagDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
