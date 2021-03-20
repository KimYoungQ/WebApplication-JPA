package com.webapplication.post;

import com.webapplication.account.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/post")
public class PostController {

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("content", new Content());
        return "post/write";
    }

    @PostMapping("/write")
    public String write(@Valid @ModelAttribute Content content, Errors errors) {
        if(errors.hasErrors()) {
            return "post/write";
        }


    }
}
