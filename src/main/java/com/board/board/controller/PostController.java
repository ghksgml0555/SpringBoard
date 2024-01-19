package com.board.board.controller;

import com.board.board.domain.Member;
import com.board.board.domain.Post;
import com.board.board.dto.CommentDto;
import com.board.board.dto.PostDto;
import com.board.board.service.CommentService;
import com.board.board.service.MemberService;
import com.board.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final CommentService commentService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails user, Model model,
                       @PageableDefault(sort="id", direction = Sort.Direction.DESC, size = 5) Pageable pageable){
        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }

        model.addAttribute("posts",postService.pageList(pageable));
        //model.addAttribute("posts",postService.findAll());
        return "home";
    }

    @GetMapping("/post/write")
    public String writeForm(@AuthenticationPrincipal UserDetails user, Model model){
        Member member = memberService.findByName(user.getUsername());
        model.addAttribute("name", user.getUsername());
        model.addAttribute("member",member);
        model.addAttribute("PostDto", new PostDto.Request());
        return "post/writePostForm";
    }

    @PostMapping("/post/write")
    public String write(PostDto.Request dto,@AuthenticationPrincipal UserDetails user){
        Member member = memberService.findByName(user.getUsername());
        dto.setMember(member);
        postService.write(dto);
        return "redirect:/";
    }

    @GetMapping("/post/read/{post_id}")
    public String readPost(@PathVariable("post_id")Long postId,@AuthenticationPrincipal UserDetails user, Model model){
        postService.updateView(postId);

        if(user != null) {
            Member member = memberService.findByName(user.getUsername());
            model.addAttribute("member", member);
            model.addAttribute("name", user.getUsername());
        }
        else{
            Member admin = memberService.findByName("admin");
            model.addAttribute("member", admin);
        }

        PostDto.Response dto = postService.findById(postId);
        model.addAttribute("postId", postId);
        model.addAttribute("dto", dto);

        /**
         * 댓글
         */
        List<CommentDto.Response> comments = dto.getComments();
        if (comments != null && !comments.isEmpty()) {
            model.addAttribute("comments", comments);
        }

        model.addAttribute("CommentDto", new CommentDto.Request());
        return "/post/read";
    }

    /**
     * 댓글 작성
     */
    @PostMapping("/post/{post_id}/comment")
    public String writeComment(@PathVariable("post_id")Long postId,
                               CommentDto.Request dto,
                               @AuthenticationPrincipal UserDetails user,
                               Model model){
        commentService.commentWrite(dto,postId,user);
        PostDto.Response postDto = postService.findById(postId);
        model.addAttribute("dto",postDto);
        Member member = memberService.findByName(user.getUsername());
        model.addAttribute("member", member);
        model.addAttribute("postId", postId);
        model.addAttribute("CommentDto", new CommentDto.Request());
        List<CommentDto.Response> comments = postDto.getComments();
        if (comments != null && !comments.isEmpty()) {
            model.addAttribute("comments", comments);
        }
        return "/post/read";
    }



    @GetMapping("/post/read/{post_id}/remove")
    public String removePost(@PathVariable("post_id")Long postId){
        postService.removePost(postId);
        return "redirect:/";
    }

    @GetMapping("/post/{post_id}/update")
    public String updatePostForm(@PathVariable("post_id")Long postId, @AuthenticationPrincipal UserDetails user, Model model){
        PostDto.Response dto = postService.findById(postId);
        model.addAttribute("dto", dto);
        model.addAttribute("name", user.getUsername());
        return "/post/update";
    }

    @PostMapping("/post/{post_id}/update")
    public String updatePost(PostDto.Request dto,@PathVariable("post_id")Long postId, Model model, @AuthenticationPrincipal UserDetails user){
        postService.updatePost(postId, dto.getTitle(), dto.getContent(), dto.getCategory());
        PostDto.Response ResponseDto = postService.findById(postId);

        Member member = memberService.findByName(user.getUsername());
        model.addAttribute("dto", ResponseDto);
        model.addAttribute("member",member);
        return "/post/read";
    }

    /**
     * 검색
     */
    @GetMapping("post/search")
    public String search(String keyword, String searchCage ,Model model,
                         @AuthenticationPrincipal UserDetails user,
                         @PageableDefault(sort="id", direction = Sort.Direction.DESC, size = 5) Pageable pageable){
        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }
        model.addAttribute("searchCage", searchCage);
        model.addAttribute("keyword", keyword);
        Page<Post> searchResult = postService.search(keyword,searchCage ,pageable);
        model.addAttribute("searchResult",searchResult);
        return "/post/search";
    }

}
