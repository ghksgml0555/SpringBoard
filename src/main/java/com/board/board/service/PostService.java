package com.board.board.service;

import com.board.board.domain.Member;
import com.board.board.domain.Post;
import com.board.board.dto.PostDto;
import com.board.board.repository.MemberRepository;
import com.board.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long write(PostDto.Request dto){
        Post post = dto.toEntity();
        postRepository.save(post);

        return post.getId();
    }

    @Transactional(readOnly = true)
    public List<PostDto.Response> findAll() {
        return postRepository.findAll().stream()
                .map(PostDto.Response::new)
                .collect(Collectors.toList());
    }

}
