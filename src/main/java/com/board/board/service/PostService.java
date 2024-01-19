package com.board.board.service;

import com.board.board.domain.Post;
import com.board.board.dto.PostDto;
import com.board.board.repository.MemberRepository;
import com.board.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public PostDto.Response findById(Long id){
        Post post = postRepository.findById(id).orElseThrow(()->
            new IllegalArgumentException("게시물이 존재하지 않음"));
        return new PostDto.Response(post);
    }

    @Transactional
    public int updateView(Long id){
        return postRepository.updateView(id);
    }

    @Transactional
    public void removePost(Long id){
        postRepository.deleteById(id);
    }

    @Transactional
    public void updatePost(Long id, String title, String content, String category){
        Post post = postRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("게시물이 존재하지 않음"));
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);
    }

    /**
     * 페이징
     */
    @Transactional(readOnly = true)
    public Page<Post> pageList(Pageable pageable){
        return postRepository.findAll(pageable);
    }

    /**
     * 검색
     */
    @Transactional
    public Page<Post> search(String titleSearch,String searchCage, Pageable pageable){
        if(searchCage==""){
            Page<Post> posts = postRepository.findByTitleContaining(titleSearch, pageable);
            return posts;
        }
        else {
            Page<Post> posts = postRepository.findByTitleContainingAndCategory(titleSearch, searchCage,pageable);
            return posts;
        }

    }
}
