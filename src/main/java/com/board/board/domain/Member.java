package com.board.board.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name="login_id")
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "writer")
    private List<Post> post;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> comment;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberLikePost> memberLikePosts;



}
