package com.board.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

   /* @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails user, Model model){
        log.info("sad {}",user);
        if(user != null){
            model.addAttribute("name",user.getUsername());
        }

        return "home";
    }*/
}
