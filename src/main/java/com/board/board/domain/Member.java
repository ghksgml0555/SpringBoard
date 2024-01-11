package com.board.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "writerId")
    private List<Post> post;

    @OneToMany(mappedBy = "member")
    private List<Comment> comment;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberLikePost> memberLikePosts;







}
