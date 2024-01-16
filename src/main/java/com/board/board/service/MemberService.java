package com.board.board.service;

import com.board.board.domain.Member;
import com.board.board.dto.MemberDto;
import com.board.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(MemberDto memberDto){
        memberRepository.save(memberDto.toEntity());
        return memberDto.toEntity().getId();
    }

    @Transactional
    public Member findOne(Long memberId){
        return memberRepository.findById(memberId).get();
    }

    @Transactional
    public Optional<Member> findLoginId(String loginId){
        return memberRepository.findByLoginId(loginId);
    }

    @Transactional
    public Member findByName(String name){
        return memberRepository.findByName(name).get();
    }

}
