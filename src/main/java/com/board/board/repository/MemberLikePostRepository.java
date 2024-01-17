package com.board.board.repository;

import com.board.board.domain.MemberLikePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberLikePostRepository extends JpaRepository<MemberLikePost, Long> {

}
