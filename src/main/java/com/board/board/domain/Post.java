package com.board.board.domain;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(name="like_count")
    private int likeCount;

    private int view;


    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<MemberLikePost> memberLikePosts;


    @ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.ALL)
    @JoinColumn(name = "writer")
    private Member writer;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToOne(mappedBy = "post")
    private Category category;


}
