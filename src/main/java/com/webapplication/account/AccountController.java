package com.webapplication.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final JoinFormValidator joinFormValidator;

    @InitBinder("joinForm")
    public void  initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(joinFormValidator);
    }

    @GetMapping("/join")
    public String join() {
        return "account/join";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute JoinForm joinForm, Errors errors) {
        if (errors.hasErrors()) {
            return "account/join";
        }

        return "redirect:/";
    }
}
