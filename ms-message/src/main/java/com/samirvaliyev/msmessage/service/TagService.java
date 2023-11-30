package com.samirvaliyev.msmessage.service;

import com.samirvaliyev.msmessage.dao.entity.TagEntity;
import com.samirvaliyev.msmessage.dao.repository.TagRepository;
import com.samirvaliyev.msmessage.mapper.TagMapper;
import com.samirvaliyev.msmessage.model.dto.SaveTagDto;
import com.samirvaliyev.msmessage.model.dto.TagDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public void saveTag(SaveTagDto dto) {
        tagRepository.save(TagMapper.mapSaveTagRequestToEntity(dto));
    }

    public List<TagEntity> getTagsByIds(Set<Long> ids) {
        return tagRepository.findByIdIn(ids);
    }

    public List<TagDto> getTags() {
        return tagRepository.findAll()
                .stream()
                .map(TagMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
