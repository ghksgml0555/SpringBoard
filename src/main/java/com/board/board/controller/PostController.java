package com.board.board.controller;

import com.board.board.domain.Member;
import com.board.board.domain.Post;
import com.board.board.dto.PostDto;
import com.board.board.service.MemberService;
import com.board.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails user, Model model){
        if(user != null){
            model.addAttribute("name",user.getUsername());
        }
        model.addAttribute("posts",postService.findAll());
        return "home";
    }

    @GetMapping("/posts/write")
    public String writeForm(@AuthenticationPrincipal UserDetails user, Model model){
        Member member = memberService.findByName(user.getUsername());
        model.addAttribute("member",member);
        model.addAttribute("PostDto", new PostDto.Request());
        return "post/writePostForm";
    }

    @PostMapping("/posts/write")
    public String write(PostDto.Request dto,@AuthenticationPrincipal UserDetails user){
        Member member = memberService.findByName(user.getUsername());
        dto.setMember(member);
        postService.write(dto);
        return "redirect:/";
    }

    @GetMapping("/posts/read/{post_id}")
    public String readPost(@PathVariable("post_id")Long postId)

}
