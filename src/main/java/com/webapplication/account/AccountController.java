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
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @InitBinder("joinForm")
    public void  initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(joinFormValidator);
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("joinForm", new JoinForm());
        return "account/join";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute JoinForm joinForm, Errors errors) {
        if (errors.hasErrors()) {
            return "account/join";
        }

        Account account = accountService.saveNewAccount(joinForm);
        accountService.login(account);
        return "redirect:/";
    }


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}
