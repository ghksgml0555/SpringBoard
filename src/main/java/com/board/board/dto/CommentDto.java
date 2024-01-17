package com.board.board.dto;

import com.board.board.domain.Comment;
import com.board.board.domain.Member;
import com.board.board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class CommentDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long id;
        private String text;
        private Post post;
        private Member member;

        public Comment toEntity(){
            Comment comment = Comment.builder()
                    .id(id)
                    .text(text)
                    .post(post)
                    .member(member)
                    .build();

            return comment;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private Long id;
        private String text;
        private String name;
        private Long postId;

        /**
         * entity > dto
         */
        public Response(Comment comment){
            this.id = comment.getId();
            this.text = comment.getText();
            this.name= comment.getMember().getName();
            this.postId = comment.getPost().getId();
        }
    }
}
