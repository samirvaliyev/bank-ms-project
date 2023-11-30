package com.samirvaliyev.msmessage.dao.repository;

import com.samirvaliyev.msmessage.dao.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    List<TagEntity> findByIdIn(Set<Long> ids);
}
