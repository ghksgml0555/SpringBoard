package com.board.board.config;

import com.board.board.domain.Member;
import com.board.board.service.MemberService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class MyUserDetailService implements UserDetailsService {

    private final MemberService memberService;

    public MyUserDetailService(MemberService memberService){
        this.memberService =memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String insertedUserId) throws UsernameNotFoundException {
        Optional<Member> findOne = memberService.findLoginId(insertedUserId);
        Member member = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다"));

        return User.builder()
                .username(member.getName())
                .password(member.getPassword())
                .build();
    }
}


