package com.samirvaliyev.msmessage.dao.repository;

import com.samirvaliyev.msmessage.dao.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByPostId(Long postId);
}
