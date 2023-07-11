package com.kr.tooit.domain.vote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tooit/vote")
public class VoteViewController {

    @GetMapping("/write")
    public String voteWrite() {
        return "voteWrite";
    }
}
