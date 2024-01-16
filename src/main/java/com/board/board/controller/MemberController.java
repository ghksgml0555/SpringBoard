package com.board.board.controller;


import com.board.board.dto.MemberDto;
import com.board.board.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/new")
    public String createForm(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "member/createMemberForm";
    }

    @PostMapping("member/new")
    public String join(@ModelAttribute @Valid MemberDto memberDto,
                       BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "member/createMemberForm";
        }
        memberService.join(memberDto);
        return "redirect:/";
    }
}
