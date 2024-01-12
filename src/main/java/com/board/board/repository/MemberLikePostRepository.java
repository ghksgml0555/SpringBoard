package com.board.board.repository;

import com.board.board.domain.MemberLikePost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLikePostRepository extends JpaRepository<MemberLikePost, Long> {
}
