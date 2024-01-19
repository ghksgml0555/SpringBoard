package com.board.board.repository;

import com.board.board.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Modifying
    @Query("update Post p set p.view = p.view+1 where p.id = :id")
    int updateView(Long id);

    Page<Post> findByTitleContaining(String titleSearch, Pageable pageable);

    Page<Post> findByTitleContainingAndCategory(String titleSearch,String category ,Pageable pageable);



}
