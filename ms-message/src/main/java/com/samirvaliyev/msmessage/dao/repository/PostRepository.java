package com.samirvaliyev.msmessage.dao.repository;

import com.samirvaliyev.msmessage.dao.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
