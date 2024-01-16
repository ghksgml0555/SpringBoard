package com.board.board.dto;

import com.board.board.domain.Member;
import com.board.board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PostDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long id;
        private String title;
        private String content;
        private int likeCount;
        private int view;
        private Member member;

        private Long member_id;

        private String category;
        //dto > entity
        public Post toEntity(){
            Post post = Post.builder()
                    .id(id)
                    .title(title)
                    .content(content)
                    .likeCount(0)
                    .view(0)
                    .writer(member)
                    .category(category)
                    .build();
            return post;
        }
    }

    /**
     * 게시물 정보를 리턴
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private Long id;
        private Long member_id;
        private String title;
        private String content;
        private String writer;
        private int likeCount;
        private int view;
        private String category;

        //entity > dto
        public Response(Post post){
            this.id = post.getId();
            this.member_id = post.getWriter().getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.writer = post.getWriter().getName();
            this.view = post.getView();
            this.likeCount = post.getLikeCount();
            this.category = post.getCategory();
        }
    }
}

