package com.webapplication.main;

import com.webapplication.post.Content;
import com.webapplication.post.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ContentRepository contentRepository;

    @GetMapping("/")
    public String main(Model model) {

        List<Content> contentList = contentRepository.findAll();
        model.addAttribute("contentList", contentList);

        return "index";
    }
}
