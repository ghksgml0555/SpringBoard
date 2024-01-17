package com.board.board.service;

import com.board.board.domain.Comment;
import com.board.board.dto.CommentDto;
import com.board.board.repository.CommentRepository;
import com.board.board.repository.MemberRepository;
import com.board.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long commentWrite(CommentDto.Request dto,
                             Long postId,
                             @AuthenticationPrincipal UserDetails user){

        dto.setMember(memberRepository.findByName(user.getUsername()).get());
        dto.setPost(postRepository.findById(postId).get());
        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return comment.getId();
    }
}
